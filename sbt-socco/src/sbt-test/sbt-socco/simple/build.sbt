version := "0.1"
scalaVersion := "2.12.13"

soccoOut := target.value / "socco"
soccoOnCompile := true

enablePlugins(SbtSoccoPlugin)
