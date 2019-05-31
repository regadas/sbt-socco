import _root_.io.regadas.sbt.SbtSoccoKeys._

inThisBuild(
  List(
    organization := "io.regadas",
    licenses := Seq(
      "Apache-2.0" -> url("http://www.apache.org/licenses/LICENSE-2.0")
    ),
    developers := List(
      Developer(
        "regadas",
        "Filipe Regadas",
        "filiperegadas@gmail.com",
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
  .enablePlugins(GhpagesPlugin)

lazy val `sbt-socco` = project
  .in(file("sbt-socco"))
  .settings(publishSettings)

lazy val `socco-examples` = project
  .in(file("socco-examples"))
  .settings(
    publish / skip := true,
    soccoOnCompile := true,
    soccoOut := target.value / "socco",
    soccoPackage += "scala:http://www.scala-lang.org/api/current/",
    makeSite / mappings ++= soccoOut.value.listFiles.map(f => (f, f.getName)).toSeq,
    makeSite := makeSite.dependsOn(Compile / compile).value
  )
  .enablePlugins(SbtSoccoPlugin)

lazy val publishSettings: Seq[Def.Setting[_]] = Def.settings(
  publishMavenStyle := false,
  bintrayRepository := "sbt-plugins",
  bintrayOrganization in bintray := None,
  bintrayReleaseOnPublish := false
)
