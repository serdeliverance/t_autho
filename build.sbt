import Dependencies._

ThisBuild / scalaVersion     := "2.13.6"
ThisBuild / version          := "0.1.0-SNAPSHOT"
ThisBuild / organization     := "com.challenge"
ThisBuild / organizationName := "authorizer"

lazy val root = (project in file("."))
  .settings(
    name := "authorizer",
  )

libraryDependencies ++= Seq(
    "org.typelevel" %% "cats-effect" % "3.2.9",
    // Test
    "org.typelevel" %% "munit-cats-effect-3" % "1.0.6" % Test,
    "org.mockito"            % "mockito-core"        % "3.5.13" % Test,
    "org.mockito"            %% "mockito-scala"        % "1.16.42" % Test,
    scalaTest % Test
)

Test / parallelExecution := false