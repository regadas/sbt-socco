lazy val `sbt-socco` = ProjectRef(file("../sbt-socco"), "sbt-socco")

dependsOn(`sbt-socco`)

addSbtPlugin("com.github.sbt" % "sbt-ci-release" % "1.9.0")
addSbtPlugin("com.github.sbt" % "sbt-site" % "1.7.0")
addSbtPlugin("com.github.sbt" % "sbt-ghpages" % "0.8.0")
addSbtPlugin("org.scalameta" % "sbt-scalafmt" % "2.5.3")