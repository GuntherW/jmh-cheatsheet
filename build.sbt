import Dependencies._

ThisBuild / scalaVersion := "2.13.6"
ThisBuild / version := "0.1.0-SNAPSHOT"
ThisBuild / organization := "de.codecentric"
ThisBuild / organizationName := "codecentric"

lazy val root = (project in file("."))
  .settings(
    name := "jmh-cheatsheet",
    libraryDependencies ++= Seq(
      "org.typelevel"          %% "cats-core"                  % "2.6.1",
      "org.scala-lang.modules" %% "scala-parallel-collections" % "1.0.3",
      "eu.timepit"             %% "refined"                    % "0.9.25"
    )
  )
  .enablePlugins(JmhPlugin)
