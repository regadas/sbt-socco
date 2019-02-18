lazy val `sbt-socco` = ProjectRef(file("../sbt-socco"), "sbt-socco")

dependsOn(`sbt-socco`)

addSbtPlugin("org.foundweekends" % "sbt-bintray" % "0.5.4")
addSbtPlugin("com.github.gseitz" % "sbt-release" % "1.0.11")