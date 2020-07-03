# sbt-socco
[![Build Status](https://travis-ci.com/regadas/sbt-socco.svg?branch=master)](https://travis-ci.com/regadas/sbt-socco)
[![Download](https://api.bintray.com/packages/regadas/sbt-plugins/sbt-socco/images/download.svg)](https://bintray.com/regadas/sbt-plugins/sbt-socco/_latestVersion)
[![License](http://img.shields.io/:license-apache-blue.svg)](http://www.apache.org/licenses/LICENSE-2.0.html)

This is an sbt plugin for [socco-ng]

> Socco is a Scala compiler plugin to generate documentation from Scala source files.
  It produces HTML documents that display your comments alongside your code. Comments are passed through Markdown, and the Scala code is syntax highlighted, typed and linked to the appropriate API Doc.
  
[socco-ng](https://github.com/socco-ng) is the updated form of [Socco] that was initially developed by our friends @ [Criteo]. 

## Usage

Start by installing the sbt plugin in project/plugins.sbt. This plugin requires sbt 1.0.0+

```scala
addSbtPlugin("io.regadas" % "sbt-socco" % "0.1.0")
```

Then in your build.sbt file, simply enable the `SbtSoccoPlugin` via an `enablePlugins` statement for your project.

```scala
enablePlugins(SbtSoccoPlugin)
```

You can further customize the generated `html` documents through with the following settings:  

* `soccoOut` - output directory for the generated `html` documents.
* `soccoStyle` - path to a custom stylesheet.
* `soccoHeader` - path to a custom `html` header.
* `soccoFooter` - path to a custom `html` footer.
* `soccoPackage` - List of packages and scaladoc url's. Needs to follow this "$packageName:$scalaDocUrl" pattern.
* `soccoIndex` - Custom task that generates a `html` toc.
* `soccoOnCompile` - If socco `html` documents should be generated at compile phase.

If you set `soccoOnCompile := false` to compile the examples you need to run:

* `enableSocco` and then,
* `compile`

## How do I write examples?

To understand the supported syntax please check [socco](https://github.com/socco-ng) repo.

## Examples

* Simple scala [app](http://gh.regadas.io/sbt-socco/Example.scala.html).
* Criteo socco [examples](https://github.com/criteo/socco#examples).

## Thanks!

Thanks [Criteo] for providing [Socco]!

[Criteo]: https://github.com/criteo
[Socco]: https://github.com/criteo/socco
