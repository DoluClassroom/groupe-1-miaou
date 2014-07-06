/*
 * Copyright (c) 2013  University of Massachusetts Amherst
 * Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0
 */

import com.davidsoergel.sbtbase.{Dependencies, DavidSoergelProject}
import sbt._
import DavidSoergelProject._


// this is just an example, to show how simple a build can be once all the boilerplate stuff is factored out.

object FooBarBuild extends Build {

  val vers = "0.1-SNAPSHOT"

  implicit val allDeps = new Dependencies()

  import allDeps._

  val deps = Seq(
    davidSoergelScalaCommons("latest.integration"),
    scalaCompiler(),
    scalatest(),
    specs2(),
    scalaIoCore(),
    scalaIoFile(),
    jdom("1.1.3"))

  lazy val scalacommons = Project("foobar", file(".")).davidSoergelSetup(vers, deps, Public, WithSnapshotDependencies).cleanLogging.standardLogging

}
