lazy val `sbt-socco` = ProjectRef(file("../sbt-socco"), "sbt-socco")

dependsOn(`sbt-socco`)

addSbtPlugin("org.foundweekends" % "sbt-bintray" % "0.6.1")
addSbtPlugin("com.github.sbt" % "sbt-release" % "1.1.0")
addSbtPlugin("com.typesafe.sbt" % "sbt-site" % "1.4.1")
addSbtPlugin("com.typesafe.sbt" % "sbt-ghpages" % "0.6.3")
addSbtPlugin("org.scalameta" % "sbt-scalafmt" % "2.5.0")
