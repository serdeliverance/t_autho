import Dependencies._

ThisBuild / scalaVersion     := "2.13.6"
ThisBuild / version          := "0.1.0-SNAPSHOT"
ThisBuild / organization     := "com.challenge"
ThisBuild / organizationName := "authorizer"

lazy val root = (project in file("."))
  .settings(
    name := "authorizer",
  )

val fs2Version = "3.2.0"
val circeVersion = "0.14.1"

libraryDependencies ++= Seq(
    "org.typelevel" %% "cats-effect" % "3.2.9",
    "io.circe" %% "circe-core" % circeVersion,
    "io.circe" %% "circe-parser" % circeVersion,
    "io.circe" %% "circe-generic" % circeVersion,
    "io.circe"     %% "circe-generic-extras" % circeVersion,
    "co.fs2" %% "fs2-core" % fs2Version,
    "co.fs2" %% "fs2-io" % fs2Version,
    "com.github.pureconfig" %% "pureconfig" % "0.17.0",
    // Test
    "org.typelevel" %% "munit-cats-effect-3" % "1.0.6" % Test,
    "org.mockito"            % "mockito-core"        % "3.5.13" % Test,
    "org.mockito"            %% "mockito-scala"        % "1.16.42" % Test,
    scalaTest % Test
)

Test / parallelExecution := false