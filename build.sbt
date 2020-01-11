scalaVersion := "2.13.1"
name := "rectangles"
organization := "io.nuvalence"
version := "1.0"

val ZIOVersion = "1.0.0-RC16"

val zioDependencies = Seq(
  "dev.zio" %% "zio" % ZIOVersion,
  "dev.zio" %% "zio-test" % ZIOVersion % "test",
  "dev.zio" %% "zio-test-sbt" % ZIOVersion % "test",
  "dev.zio" %% "zio-interop-cats" % "2.0.0.0-RC7",
  "dev.zio" %% "zio-macros-core" % "0.5.0"
)

val testDependencies =  Seq("org.scalatest" %% "scalatest" % "3.0.8" % "test")
val commonDependencies = zioDependencies ++ testDependencies

connectInput in run := true

libraryDependencies ++= commonDependencies


mainClass in Compile := Some("io.nuvalence.Main")
enablePlugins(JavaAppPackaging)
enablePlugins(DockerPlugin)