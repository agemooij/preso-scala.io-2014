import sbt._
import sbt.Keys._

import com.scalapenos.sbt.prompt._
import SbtPrompt._
import autoImport._

import spray.revolver.RevolverPlugin.Revolver

object Build extends Build {
  import Dependencies._
  import Formatting._

  lazy val basicSettings = Seq(
    organization := "com.scalapenos",
    version := "0.1.0",
    scalaVersion := "2.11.3",
    scalacOptions := basicScalacOptions,
    incOptions := incOptions.value.withNameHashing(true),
    promptTheme := PromptThemes.Scalapenos
  )

  lazy val appSettings = basicSettings ++ dependencySettings ++ formattingSettings ++ Revolver.settings

  lazy val root = Project("spray-routing-preso", file("."))
    .settings(basicSettings: _*)
    .aggregate(
      e01
    )


  lazy val e01 = Project("e01-basics", file("e01-basics"))
    .settings(appSettings: _*)
    .settings(mainClass := Some("preso.e01.basics.Main"))
    .settings(libraryDependencies ++=
      compile(akkaActor) ++
      compile(akkaSlf4j) ++
      compile(sprayCan) ++
      compile(sprayRouting) ++
      compile(sprayJson) ++
      compile(ficus) ++
      compile(logback) ++
      test(akkaTestkit) ++
      test(sprayTestkit) ++
      test(scalatest))


  // ==========================================================================
  // ScalacOptions
  // ==========================================================================

  val basicScalacOptions = Seq(
    "-encoding", "utf8",
    "-target:jvm-1.7",
    "-feature",
    "-language:implicitConversions",
    "-language:postfixOps",
    "-unchecked",
    "-deprecation",
    "-Xlog-reflective-calls"
  )

  val fussyScalacOptions = basicScalacOptions ++ Seq(
    "-Ywarn-unused",
    "-Ywarn-unused-import"
  )
}
