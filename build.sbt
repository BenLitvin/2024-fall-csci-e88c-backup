import Dependencies._

ThisBuild / organization := "org.cscie88c"
ThisBuild / scalaVersion := "2.13.14"
ThisBuild / semanticdbEnabled := true // enable SemanticDB
ThisBuild / semanticdbVersion := scalafixSemanticdb.revision // use Scalafix compatible version

lazy val root = (project in file(".")).settings(
  name := "2024FallScalaBigData",
  scalacOptions ++= Seq(
    "-feature",
    "-deprecation",
    "-unchecked",
    "-language:postfixOps",
    "-language:higherKinds", // HKT required for Monads and other HKT types
    "-Wunused" // for scalafix
  ),
  Compile / run / fork := true, // cleaner to run programs in a JVM different from sbt
  run / javaOptions += "--add-exports=java.base/sun.nio.ch=ALL-UNNAMED", // needed to run Spark with Java 17

  // *** Added to resolve Jackson Databind version conflict ***
  libraryDependencies ++= Seq(
    "com.fasterxml.jackson.core" % "jackson-databind" % "2.14.1",
    "com.fasterxml.jackson.module" %% "jackson-module-scala" % "2.14.1"
  ),
  dependencyOverrides += "com.fasterxml.jackson.core" % "jackson-databind" % "2.14.1",
  // *** End of added modifications ***

// *** Added to resolve conflict for Bonus section ***
  libraryDependencies += "io.netty" % "netty-all" % "4.1.100.Final" exclude ("io.netty", "netty-handler"),
  dependencyOverrides += "io.netty" % "netty-all" % "4.1.100.Final",
  
  // *** End of added modifications ***

  libraryDependencies ++= Dependencies.core ++ Dependencies.scalaTest,
  assembly / mainClass := Some("org.cscie88c.MainApp"),
  assembly / assemblyJarName := "2024FallScalaBigData.jar",
  assembly / test := {},
  assembly / assemblyMergeStrategy := {
    case PathList("META-INF", xs @ _*) => MergeStrategy.discard
    case "application.conf"            => MergeStrategy.concat
    case x =>
      val oldStrategy = (assembly / assemblyMergeStrategy).value
      oldStrategy(x)
  },
  // see shading feature at https://github.com/sbt/sbt-assembly#shading
  assembly / assemblyShadeRules := Seq(
    ShadeRule.rename("shapeless.**" -> "shadeshapeless.@1").inAll
  )
)

// Custom task to zip files for homework submission
lazy val zipHomework = taskKey[Unit]("zip files for homework submission")

zipHomework := {
  val bd = baseDirectory.value
  val targetFile = s"${bd.getAbsolutePath}/scalaHomework.zip"
  val ignoredPaths =
    ".*(\\.idea|target|\\.DS_Store|\\.bloop|\\.metals|\\.vsc)/*".r.pattern
  val fileFilter = new FileFilter {
    override def accept(f: File) =
      !ignoredPaths.matcher(f.getAbsolutePath).lookingAt
  }
  println("zipping homework files to scalaHomework.zip ...")
  IO.delete(new File(targetFile))
  IO.zip(
    Path.selectSubpaths(new File(bd.getAbsolutePath), fileFilter),
    new File(targetFile)
  )
}
