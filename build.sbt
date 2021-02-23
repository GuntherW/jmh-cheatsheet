import Dependencies._

ThisBuild / scalaVersion := "2.13.5"
ThisBuild / version := "0.1.0-SNAPSHOT"
ThisBuild / organization := "de.codecentric"
ThisBuild / organizationName := "codecentric"

lazy val root = (project in file("."))
  .settings(
    name := "jmh-cheatsheet",
    libraryDependencies ++= Seq(
      "org.typelevel"          %% "cats-core"                  % "2.4.2",
      "org.scala-lang.modules" %% "scala-parallel-collections" % "1.0.0",
      "eu.timepit"             %% "refined"                    % "0.9.21"
    )
  )
  .enablePlugins(JmhPlugin)
