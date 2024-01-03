import Dependencies._

ThisBuild / scalaVersion := "2.13.12"
ThisBuild / version := "0.1.0-SNAPSHOT"
ThisBuild / organization := "de.codecentric"
ThisBuild / organizationName := "codecentric"

lazy val root = (project in file("."))
  .settings(
    name := "jmh-cheatsheet",
    libraryDependencies ++= Seq(
      "org.typelevel"          %% "cats-core"                  % "2.10.0",
      "org.scala-lang.modules" %% "scala-parallel-collections" % "1.0.4",
      "eu.timepit"             %% "refined"                    % "0.11.0"
    )
  )
  .enablePlugins(JmhPlugin)
