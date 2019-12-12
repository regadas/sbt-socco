package io.regadas.sbt

import sbt.Keys._
import sbt._
import sbt.plugins.JvmPlugin
import com.typesafe.sbt.site.SitePlugin

object SbtSoccoPlugin extends AutoPlugin {
  val SoccoCompilerPlugin: ModuleID = "com.criteo.socco" %% "socco-plugin" % "0.1.9"

  override def trigger: PluginTrigger = noTrigger
  override def requires = JvmPlugin && SitePlugin

  val autoImport = SbtSoccoKeys
  import autoImport._
  import SitePlugin.autoImport._

  override lazy val projectSettings = soccoSettings(ThisProject)

  override lazy val globalSettings = Def.settings(commands += enableSoccoCommand)

  def soccoSettings(p: ProjectReference, onCompile: Boolean = false) =
    Def.settings(
      soccoOnCompile.in(p) := onCompile,
      soccoPackage := Nil,
      soccoOut := target.value / "socco",
      scalacOptions.in(p) ++= Def.taskDyn {
        if (soccoOnCompile.in(p).value) {
          Def.task(
            Seq(
              soccoOut.in(p).?.map(_.map(s"-P:socco:out:" + _)).value,
              soccoHeader.in(p).?.map(_.map(s"-P:socco:header:" + _)).value,
              soccoFooter.in(p).?.map(_.map(s"-P:socco:footer:" + _)).value,
              soccoStyle.in(p).?.map(_.map(s"-P:socco:style:" + _)).value
            ).flatten ++ soccoPackage
              .in(p)
              .map(_.map(s"-P:socco:package_" + _))
              .value)
        } else {
          Def.task(Nil: Seq[String])
        }
      }.value,
      autoCompilerPlugins.in(p) := true,
      libraryDependencies.in(p) ++= {
        if (soccoOnCompile.in(p).value) {
          Seq(compilerPlugin(SoccoCompilerPlugin))
        } else {
          Nil
        }
      },
      (Compile / compile).in(p) := Def.taskDyn {
        val default = (Compile / compile).in(p).taskValue
        if (soccoOnCompile.in(p).value) {
          //Generate scio-examples/target/site/index.html
          Def.task {
            soccoIndex.in(p).?.value
            default.value
          }
        } else {
          Def.task(default.value)
        }
      }.value,
      makeSite / mappings ++= soccoOut.value.listFiles.map(f => (f, f.getName)).toSeq
    )

  private[this] def projectsWithSocco(state: State): Seq[(ProjectRef, Boolean)] = {
    val extracted = Project.extract(state)
    for {
      p <- extracted.structure.allProjectRefs
      onCompile <- soccoOnCompile.in(p).get(extracted.structure.data)
    } yield p -> onCompile
  }

  private[this] lazy val enableSoccoCommand = Command.command("enableSocco") { s =>
    val extracted = Project.extract(s)
    val settings: Seq[Setting[_]] = for {
      (p, onCompile) <- projectsWithSocco(s)
      if !onCompile
      setting <- soccoSettings(p, onCompile = true)
    } yield setting

    extracted.appendWithoutSession(settings, s)
  }

}

object SbtSoccoKeys {
  val soccoOut = settingKey[File]("to specify the output directory")
  val soccoStyle = settingKey[File]("path to specify a custom stylesheet")
  val soccoHeader = settingKey[File]("path to specify a custom HTML header")
  val soccoFooter = settingKey[File]("path to specify a custom HTML footer")
  val soccoPackage =
    settingKey[List[String]]("$packageName:$scalaDocUrl to specify a Scala API doc to link")
  val soccoIndex = taskKey[File]("Generates examples/index.html")
  val soccoOnCompile: SettingKey[Boolean] = settingKey[Boolean]("Socco doc's")
}
