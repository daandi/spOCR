name := "spOCR"

scalaVersion := "2.9.2"

scalacOptions += "-deprecation"

libraryDependencies ++= Seq(
    "org.ccil.cowan.tagsoup" % "tagsoup" % "1.2",
    "org.specs2" %% "specs2" % "1.12.1" % "test"
)

