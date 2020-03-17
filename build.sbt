name := "scalaTesttry"

version := "0.1"

scalaVersion := "2.12.10"
libraryDependencies ++= Seq(
  "org.apache.spark" %% "spark-core" % "3.0.0-preview2",
  "org.apache.spark" %% "spark-sql" % "3.0.0-preview2",
  "org.scalatest" %% "scalatest" % "3.0.8" % "test"
)