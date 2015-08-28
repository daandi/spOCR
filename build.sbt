name := "spOCR"

organization := "biz.neumann"

scalaVersion := "2.11.7"

scalacOptions += "-deprecation"

libraryDependencies ++= Seq(
  "org.ccil.cowan.tagsoup" % "tagsoup" % "1.2.1",
  "org.scala-lang.modules" % "scala-xml_2.11" % "1.0.5",
  "org.specs2" %% "specs2-core" % "3.6.4" % "test"
)

publishMavenStyle := true

publishTo := {
  val nexus = "https://oss.sonatype.org/"
  if (isSnapshot.value)
    Some("snapshots" at nexus + "content/repositories/snapshots")
  else
    Some("releases" at nexus + "service/local/staging/deploy/maven2")
}

publishMavenStyle := true

publishArtifact in Test := false

pomIncludeRepository := { _ => false }

pomExtra := (
  <url>https://github.com/daandi/spOCR</url>
  <licenses>
    <license>
      <name>BSD-style</name>
      <url>http://www.opensource.org/licenses/bsd-license.php</url>
      <distribution>repo</distribution>
    </license>
  </licenses>
  <scm>
    <url>git@github.com:daandi/spOCR.git</url>
    <connection>scm:git:git@github.com:daandi/spOCR.git</connection>
  </scm>
  <developers>
    <developer>
      <id>daandi</id>
      <name>Andreas Neumann</name>
      <url>http://www.neumann,biz</url>
    </developer>
  </developers>
)

