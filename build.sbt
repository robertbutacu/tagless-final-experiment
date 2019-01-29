name := "tagless-final-experiment"

version := "0.1"

scalaVersion := "2.12.8"
resolvers += "Sonatype OSS Snapshots" at "https://oss.sonatype.org/content/repositories/snapshots"

libraryDependencies ++= Seq(
  "eu.timepit"               %% "refined"    % "0.9.4",
  "com.olegpy"               %% "meow-mtl"   % "0.2.0",
  "org.scalacheck"           %% "scalacheck" % "1.14.0",
  "com.softwaremill.macwire" %% "util"       % "2.3.1",
  "com.softwaremill.macwire" %% "macros"     % "2.3.1"
)