import sbt._

lazy val `sbt-socco` = project
  .in(file("."))
  .settings(
    name := "sbt-socco",
    sbtPlugin := true,
    scriptedBufferLog := false,
    initialCommands in console := """import io.regadas.sbt._""",
    scriptedLaunchOpts ++= Seq("-Xmx1024M", "-Dplugin.version=" + version.value)
  )
  .enablePlugins(ScriptedPlugin)
