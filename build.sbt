scalaVersion := "2.12.8"
name := "dummy-runtime"
version := IO.read(file("version")).trim

organization := "io.hydrosphere.serving"
scalacOptions ++= Seq(
  "-unchecked",
  "-deprecation",
  "-feature",
  "-language:existentials",
  "-language:higherKinds",
  "-language:implicitConversions",
  "-language:postfixOps",
  "-Ypartial-unification"
)
enablePlugins(JavaAppPackaging)
mainClass in Compile := Some("io.hydrosphere.serving.dummy_runtime.DummyRuntimeApp")

enablePlugins(DockerSpotifyClientPlugin)

lazy val logDependencies = Seq(
  "org.apache.logging.log4j" % "log4j-api" % "2.8.2",
  "org.apache.logging.log4j" % "log4j-core" % "2.8.2",
  "org.apache.logging.log4j" % "log4j-slf4j-impl" % "2.8.2",
  "org.apache.logging.log4j" %% "log4j-api-scala" % "11.0"
)

lazy val grpcDependencies = Seq(
  "com.thesamet.scalapb" %% "scalapb-runtime-grpc" % scalapb.compiler.Version.scalapbVersion exclude("com.google.api.grpc", "proto-google-common-protos"),
  "io.hydrosphere" %% "serving-grpc-scala" % "2.0.0-rc7dev" exclude("com.google.api.grpc", "proto-google-common-protos"),
  "io.grpc" % "grpc-netty" % scalapb.compiler.Version.grpcJavaVersion exclude("com.google.api.grpc", "proto-google-common-protos")
)

libraryDependencies ++= logDependencies ++ grpcDependencies

packageName in Docker := "hydrosphere/serving-runtime-dummy"
daemonUser in Docker := "daemon"
dockerBaseImage := "openjdk:8-jre-alpine"
dockerEnvVars := Map(
  "APP_PORT" -> "9090",
  "SIDECAR_PORT" -> "8080",
  "SIDECAR_HOST" -> "localhost",
  "MODEL_DIR" -> "/model"
)
dockerLabels := Map(
  "DEPLOYMENT_TYPE" -> "APP"
)
dockerExposedVolumes := Seq("/model")
dockerExposedPorts := Seq(9090)