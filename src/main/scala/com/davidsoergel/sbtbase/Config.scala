/*
 * Copyright (c) 2013  University of Massachusetts Amherst
 * Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0
 */

package com.davidsoergel.sbtbase

import sbt._

/**
 * @author <a href="mailto:dev@davidsoergel.com">David Soergel</a>
 * @version $Id$
 */

object Config {

  val davidsoergel = "com.davidsoergel"
  val scalaV = "2.10.3"

  // any needed 3rd-party repositories should by proxied in Nexus and added to the public group, so their artifacts will be available here automatically.
  // Release and Snapshot repos are separated, to allow easily insuring that no snapshot dependencies exist (by default, in fact)

  val nexusUrl = "http://dev.davidsoergel.com/nexus"
  val nexusHttpsUrl = "https://dev.davidsoergel.com/nexus"

  val DavidSoergelReleaseRepos = Seq(
    "David Soergel Public Releases" at  nexusUrl + "/content/groups/public",
    "David Soergel Private Releases" at nexusUrl + "/content/repositories/private-releases")

  val DavidSoergelSnapshotRepos = Seq(
    "David Soergel Public Snapshots" at nexusUrl + "/content/groups/public-snapshots",
    "David Soergel Private Snapshots" at  nexusUrl + "content/repositories/private-snapshots")

  val publishRealm: String = "Sonatype Nexus Repository Manager"
  val publishHost: String = "dev.davidsoergel.com"
}
