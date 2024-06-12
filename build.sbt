import Dependencies._

ThisBuild / scalaVersion     := "3.4.2"
ThisBuild / version          := "0.1.0-SNAPSHOT"
ThisBuild / organization     := "de.codecentric"
ThisBuild / organizationName := "codecentric"

lazy val root = (project in file("."))
  .settings(
    name := "jmh-cheatsheet",
    libraryDependencies ++= Seq(
      "org.typelevel"          %% "cats-core"                  % "2.12.0",
      "org.scala-lang.modules" %% "scala-parallel-collections" % "1.0.4",
      "eu.timepit"             %% "refined"                    % "0.11.2"
    )
//    scalacOptions ++= Seq(
//      "-rewrite",
//      "-source:3.4-migration"
//    )
  )
  .enablePlugins(JmhPlugin)
