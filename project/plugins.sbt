addSbtPlugin("io.spray" % "sbt-revolver" % "0.7.2")

addSbtPlugin("com.eed3si9n" % "sbt-buildinfo" % "0.3.2")

addSbtPlugin("com.typesafe.sbt" % "sbt-scalariform" % "1.3.0")

addSbtPlugin("com.scalapenos" % "sbt-prompt" % "0.1")

resolvers += "laughedelic maven releases" at "http://dl.bintray.com/laughedelic/maven"

resolvers ++= Seq(
  "laughedelic maven releases" at "http://dl.bintray.com/laughedelic/maven"
, Resolver.url("laughedelic sbt-plugins", url("http://dl.bintray.com/laughedelic/sbt-plugins"))(Resolver.ivyStylePatterns)
)

addSbtPlugin("laughedelic" % "literator-plugin" % "0.6.0")
