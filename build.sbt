import AssemblyKeys._


name := "nvector"

version := "0.1"

scalaVersion := "2.11.6"

scalacOptions ++= Seq( "-deprecation", "-feature", "-language:postfixOps", "-language:implicitConversions", "-language:existentials", "-language:reflectiveCalls" )

incOptions := incOptions.value.withNameHashing( true )

organization := "ca.hyperreal"

//resolvers += Resolver.sonatypeRepo( "snapshots" )

resolvers += "Typesafe Repository" at "http://repo.typesafe.com/typesafe/releases/"

resolvers += "Hyperreal Repository" at "https://dl.bintray.com/edadma/maven"

libraryDependencies += "org.scalatest" %% "scalatest" % "2.2.1" % "test"

libraryDependencies += "org.scalacheck" %% "scalacheck" % "1.11.5" % "test"

libraryDependencies += "ca.hyperreal" %% "scalgorithms" % "0.1"

mainClass in (Compile, run) := Some( "ca.hyperreal.nvector.Main" )

assemblySettings

mainClass in assembly := Some( "ca.hyperreal.nvector.Main" )

jarName in assembly := name.value + "-" + version.value + ".jar"

seq(bintraySettings:_*)

publishMavenStyle := true

publishArtifact in Test := false

pomIncludeRepository := { _ => false }

licenses := Seq("MIT" -> url("http://opensource.org/licenses/MIT"))

homepage := Some(url("https://github.com/edadma/nvector"))

pomExtra := (
  <scm>
    <url>git@github.com:edadma/nvector.git</url>
    <connection>scm:git:git@github.com:edadma/nvector.git</connection>
  </scm>
  <developers>
    <developer>
      <id>edadma</id>
      <name>Edward A. Maxedon, Sr.</name>
      <url>http://hyperreal.ca</url>
    </developer>
  </developers>)
