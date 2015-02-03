name := "testing-hapi-hl7-scala"

version := "1.0"

scalaVersion := "2.11.5"

libraryDependencies ++= Seq(
  "org.specs2" %% "specs2-core" % "2.4.15" % "test",
  "ca.uhn.hapi" % "hapi-base" % "2.2",
  "ca.uhn.hapi" % "hapi-structures-v22" % "2.2",
  "ca.uhn.hapi" % "hapi-structures-v23" % "2.2",
  "ca.uhn.hapi" % "hapi-structures-v24" % "2.2"
)