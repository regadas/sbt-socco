import _root_.io.regadas.sbt.SbtSoccoKeys._

inThisBuild(
  List(
    organization := "io.regadas",
    scalaVersion := "2.12.19",
    licenses := Seq(
      "Apache-2.0" -> url("http://www.apache.org/licenses/LICENSE-2.0")
    ),
    developers := List(
      Developer(
        "regadas",
        "Filipe Regadas",
        "oss@regadas.email",
        url("https://twitter.com/regadas")
      )
    ),
    git.remoteRepo := "git@github.com:regadas/sbt-socco.git"
  )
)

lazy val root = project
  .in(file("."))
  .settings(
    publish / skip := true
  )
  .aggregate(`sbt-socco`, `socco-examples`)

lazy val `sbt-socco` = project
  .in(file("sbt-socco"))

lazy val `socco-examples` = project
  .in(file("socco-examples"))
  .settings(
    scalaVersion := "2.13.15",
    publish / skip := true,
    soccoOnCompile := true,
    soccoPackage += "scala:http://www.scala-lang.org/api/current/",
    makeSite := makeSite.dependsOn(Compile / compile).value
  )
  .enablePlugins(SbtSoccoPlugin)
  .enablePlugins(GhpagesPlugin)
