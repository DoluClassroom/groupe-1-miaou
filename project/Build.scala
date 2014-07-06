import sbt._
import sbt.Keys._
import java.io.File

object DavidSoergelSbtBaseBuild extends Build {

  val davidsoergel = "com.davidsoergel"
  val scalaV = "2.10.4"
  val vers = "79-ds"

  val packageTemplate = TaskKey[File]("package-template")

  val packageTemplateTask = packageTemplate <<= (target, sourceDirectory) map {
    (target, sourceDirectory) =>
      import sys.process._
      val templatePackageFilename = target + "/davidsoergel-sbt-base-template.tgz"
      val tarCommand = "tar cvzf " + templatePackageFilename + " davidsoergel-sbt-base-template"
      println(tarCommand)
      val result = Process(tarCommand,sourceDirectory).!!

      println(result)
      println("Built: " + templatePackageFilename)
      new File(templatePackageFilename)
  }

  val templateArtifact = addArtifact(Artifact("template", "tgz", "tgz"), packageTemplate)

  lazy val davidSoergelSbtBase =
    Project("davidsoergel-sbt-base", file("."))
      .settings(
      sbtPlugin := true,
      organization := davidsoergel,
      version := vers,
      scalaVersion := scalaV,
      publishToDavidSoergel(vers),
      creds).settings(packageTemplateTask.settings: _*).settings(templateArtifact.settings: _*)

  def publishToDavidSoergel(vers: String) = publishTo := {
    def repo(name: String) = name at "https://dev.davidsoergel.com/nexus/content/repositories/" + name
    val isSnapshot = vers.endsWith("SNAPSHOT")
    val repoName = (if (isSnapshot) "snapshots" else "releases")
    Some(repo(repoName))
  }

  val creds = credentials += {
    Seq("build.publish.user", "build.publish.password").map(k => Option(System.getProperty(k))) match {
      case Seq(Some(user), Some(pass)) =>
        Credentials("Sonatype Nexus Repository Manager", "dev.davidsoergel.com", user, pass)
      case _ =>
        Credentials(Path.userHome / ".ivy2" / ".credentials")
    }
  }


}
