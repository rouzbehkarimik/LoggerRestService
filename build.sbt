import com.typesafe.sbt.packager.docker.{Cmd, ExecCmd}
name := "LoggerRestService"
/*hello*/
version := "0.1"

scalaVersion := "2.11.11"

val akkaVersion = "2.5.3"



val runFrontend = taskKey[Unit]("create a new frontend")
mainClass in (Compile,packageBin) := Some("play.core.server.ProdServerStart")
fullRunTask(runFrontend, Compile, "play.core.server.ProdServerStart")
fork in runFrontend := true
libraryDependencies ++=Seq(
"com.typesafe.akka" %% "akka-actor" % akkaVersion,
"com.typesafe.akka" %% "akka-cluster" % akkaVersion,
"com.typesafe.akka" %% "akka-remote" % akkaVersion,
"com.typesafe.akka" %% "akka-cluster-metrics" % akkaVersion,
"com.typesafe.akka" %% "akka-slf4j" % akkaVersion,
"com.typesafe.akka" %% "akka-persistence" % akkaVersion,
"com.typesafe.play" % "play_2.11" % "2.5.15",
"com.spotify" % "docker-client" % "8.11.2",
"com.internetitem" % "logback-elasticsearch-appender" % "1.6",
"com.github.danielwegener" % "logback-kafka-appender" % "0.2.0-RC1",
"ch.qos.logback" % "logback-classic" % "1.2.3",
"org.slf4j" % "slf4j-api" % "1.7.25"
)

javaOptions += "-Dlogger.file=/Users/rouzbeh/logback.xml"
javaOptions += "-Xms24m"
javaOptions += "-Xmx24m"
enablePlugins(PlayJava)
enablePlugins(RpmPlugin)
/*hello*/
