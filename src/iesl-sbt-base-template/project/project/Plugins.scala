/*
 * Copyright (c) 2013  University of Massachusetts Amherst
 * Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0
 */

import sbt._
import sbt.Keys._


object DavidSoergelPluginLoader extends Build {

  // automatically updating SNAPSHOTs does not seem to work right here.
  // as a workaround, we'll just release every incremental change to iesl-sbt-base with integer build numbers.

  lazy val root = Project(id = "plugins", base = file("."))
    .settings(resolvers += "David Soergel Public Releases" at "http://dev.davidsoergel.com/nexus/content/groups/public")
    //.settings(resolvers += "IESL Public Snapshots" at "https://dev-iesl.cs.umass.edu/nexus/content/groups/public-snapshots")
    .settings(addSbtPlugin("com.davidsoergel" %% "davidsoergel-sbt-base" % "latest.release")) // apparently buggy: "latest.integration" changing()
}


// todo: test this approach again
// as of sbt 0.12.0 we can rebuild the plugin on the fly from the hg repository,
// avoiding the Nexus URL chicken-and-egg problem (or rather, pushing it back one level to the GitHub URL)

/*
object IeslPluginLoader extends Build {
  override lazy val projects = Seq(root)
  lazy val root = Project("plugins", file(".")) dependsOn( ieslSbtBase )
  lazy val ieslSbtBase = uri("hg:https://IESL@bitbucket.org/IESL/iesl-sbt-base")
}

*/
