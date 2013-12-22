name := "spOCR"

scalaVersion := "2.10.3"

scalacOptions += "-deprecation"

libraryDependencies ++= Seq(
    "org.ccil.cowan.tagsoup" % "tagsoup" % "1.2.1",
    "org.specs2" %% "specs2" % "2.3.3" % "test"
)

