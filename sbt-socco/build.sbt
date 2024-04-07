import sbt._

lazy val `sbt-socco` = project
  .in(file("."))
  .settings(
    name := "sbt-socco",
    sbtPlugin := true,
    pluginCrossBuild / sbtVersion := "1.2.8",
    scriptedBufferLog := false,
    initialCommands in console := """import io.regadas.sbt._""",
    scriptedLaunchOpts ++= Seq("-Xmx1024M", "-Dplugin.version=" + version.value)
  )
  .settings(addSbtPlugin("com.github.sbt" % "sbt-site" % "1.7.0"))
  .enablePlugins(ScriptedPlugin)
