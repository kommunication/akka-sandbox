import Dependencies._

ThisBuild / organization := "com.komlan.lab.akka"
ThisBuild / scalaVersion := "2.13.7"

lazy val `sandbox` =
  project
    .in(file("."))
    .settings(name := "sandbox")
    .settings(commonSettings)
    .settings(dependencies)

lazy val commonSettings =
  compilerPlugins ++ commonScalacOptions ++ Seq(
    update / evictionWarningOptions := EvictionWarningOptions.empty
  )

lazy val compilerPlugins = Seq(
  addCompilerPlugin(com.olegpy.`better-monadic-for`),
  addCompilerPlugin(org.augustjune.`context-applied`),
  addCompilerPlugin(org.typelevel.`kind-projector`),
)

lazy val commonScalacOptions = Seq(
  Compile / console / scalacOptions := {
    (Compile / console / scalacOptions)
      .value
      .filterNot(_.contains("wartremover"))
      .filterNot(Scalac.Lint.toSet)
      .filterNot(Scalac.FatalWarnings.toSet) :+ "-Wconf:any:silent"
  },
  Test / console / scalacOptions :=
    (Compile / console / scalacOptions).value,
)

val akkaVersion = "2.6.17"


lazy val dependencies = Seq(
  libraryDependencies ++= Seq(
    // main dependencies
    "com.typesafe.akka" %% "akka-actor" % akkaVersion,
  ),
  libraryDependencies ++= Seq(
    com.github.alexarchambault.`scalacheck-shapeless_1.15`,
    org.scalacheck.scalacheck,
    org.scalatest.scalatest,
    org.scalatestplus.`scalacheck-1-15`,
    org.typelevel.`discipline-scalatest`,
    "com.typesafe.akka" %% "akka-testkit" % akkaVersion,
  ).map(_ % Test),
)
