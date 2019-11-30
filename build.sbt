import Dependencies._

ThisBuild / scalaVersion := "2.13.1"
//ThisBuild / scalaVersion := "2.12.10"
ThisBuild / version := "0.1.0-SNAPSHOT"
ThisBuild / organization := "de.codecentric"
ThisBuild / organizationName := "codecentric"

lazy val root = (project in file("."))
  .settings(
    name := "jmh-cheatsheet",
    libraryDependencies ++= Seq(
      "org.typelevel" %% "cats-core" % "2.0.0",
      "eu.timepit"    %% "refined"   % "0.9.10"
    )
  )
  .enablePlugins(JmhPlugin)
