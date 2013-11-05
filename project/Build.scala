import sbt._
import Keys._

object ScalaBuffBuild extends Build {
  lazy val project = Project(
    id = "root", 
    base = file("."),
    settings = Defaults.defaultSettings ++ Seq(
      credentials += Credentials(Path.userHome / ".sbt" / ".credentials"),
      publishTo <<= (version) { version: String =>
        val nexus = "http://nexus.dcxft.com:8081/nexus/"
        if (version.trim.endsWith("SNAPSHOT"))
          Some("snapshots" at nexus + "content/repositories/buttercoin-snapshots")
        else
          Some("releases" at nexus + "content/repositories/buttercoin")
      },
      publishMavenStyle := true,
      publishArtifact in Test := false,
      pomIncludeRepository := { _ => false }
    )
  )
}
