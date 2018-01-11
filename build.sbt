name := """trader"""
organization := "net.misiorek.trader"

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayJava)

scalaVersion := "2.12.4"

libraryDependencies += guice

libraryDependencies ++= Seq(
	"org.web3j" % "core" % "3.2.0"
)

// https://mvnrepository.com/artifact/com.typesafe.akka/akka-actor
libraryDependencies += "com.typesafe.akka" %% "akka-actor" % "2.5.8"

libraryDependencies += "mysql" % "mysql-connector-java" % "5.1.6"

libraryDependencies ++= Seq(
  javaJdbc,
  cache,
  javaJpa,
  "org.hibernate" % "hibernate-entitymanager" % "5.1.0.Final" // replace by your jpa implementation
)

libraryDependencies ++= Seq(
	"com.h2database" % "h2" % "1.3.176",
	"javax.persistence" % "persistence-api" % "1.0"
)
