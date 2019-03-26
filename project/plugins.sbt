logLevel := Level.Info
addSbtPlugin("com.typesafe.sbt" % "sbt-native-packager" % "1.3.18")

libraryDependencies ++= Seq(
  "com.thesamet.scalapb" %% "compilerplugin" % "0.7.4",
  "com.spotify" % "docker-client" % "8.9.0"
)