name := """trader"""
organization := "net.misiorek.trader"

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayJava)

scalaVersion := "2.12.4"

libraryDependencies += guice

libraryDependencies ++= Seq(
	"org.web3j" % "core" % "3.2.0"
)

