import sbt._
import sbt.Keys._

import spray.revolver.RevolverPlugin.Revolver

object Build extends Build {
  import Dependencies._
  import Formatting._

  import com.scalapenos._
  import com.scalapenos.SbtPrompt._
  import autoImport._

  lazy val basicSettings = Seq(
    organization := "com.scalapenos",
    version := "0.1.0",
    scalaVersion := "2.11.2",
    scalacOptions := basicScalacOptions,
    incOptions := incOptions.value.withNameHashing(true),

    promptSeparator := Separator("", (previous, next) => fg(previous.background).bg(next.background)),
    promptSegments := Seq(
      text(" SBT ", fg(235).bg(26)),
      gitBranch(clean = fg(235).bg(34), dirty = fg(235).bg(214)).padLeft("  ").padRight(" "),
      currentProject(fg(250).bg(235)).pad(" "),
      text(" ", NoStyle)
    )
  )

  lazy val appSettings = basicSettings ++ dependencySettings ++ formattingSettings ++ Revolver.settings

  lazy val root = Project("spray-routing-preso", file("."))
    .settings(basicSettings: _*)
    .aggregate(
      example1
    )


  lazy val example1 = Project("example-01", file("example-01-intro"))
    .settings(appSettings: _*)
    .settings(mainClass := Some("preso.spray.routing.example1"))
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
