package preso

import spray.json._
import DefaultJsonProtocol._

case class Thing(name: String)

object Thing {
  implicit val json = jsonFormat1(Thing.apply)
}

case class Dude(id: String, name: String, tags: Set[String] = Set.empty)

object Dude {
  implicit val json = jsonFormat3(Dude.apply)
}
