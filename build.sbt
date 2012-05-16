name := "spOCR"

scalaVersion := "2.9.1"

scalacOptions += "-deprecation"

libraryDependencies ++= Seq(
	"org.scalala" % "scalala_2.9.1" % "1.0.0.RC2",
    "org.ccil.cowan.tagsoup" % "tagsoup" % "1.2",
    "org.specs2" %% "specs2" % "1.7.1" % "test"
)

resolvers ++= Seq("snapshots" at "http://oss.sonatype.org/content/repositories/snapshots",
                    "releases"  at "http://oss.sonatype.org/content/repositories/releases",
					"Scala Tools Snapshots" at "http://scala-tools.org/repo-snapshots/",
					"ScalaNLP Maven2" at "http://repo.scalanlp.org/repo"
				  )

