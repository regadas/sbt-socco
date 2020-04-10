lazy val `sbt-socco` = ProjectRef(file("../sbt-socco"), "sbt-socco")

dependsOn(`sbt-socco`)

addSbtPlugin("org.foundweekends" % "sbt-bintray" % "0.5.6")
addSbtPlugin("com.github.gseitz" % "sbt-release" % "1.0.13")
addSbtPlugin("com.typesafe.sbt" % "sbt-site" % "1.4.0")
addSbtPlugin("com.typesafe.sbt" % "sbt-ghpages" % "0.6.3")
addSbtPlugin("org.scalameta" % "sbt-scalafmt" % "2.3.4")
