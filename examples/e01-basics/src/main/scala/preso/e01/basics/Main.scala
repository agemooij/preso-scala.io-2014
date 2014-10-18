package preso.e01.basics

import akka.actor._
import akka.io.IO

import spray.can.Http

import rfs.rebb.order._

object Main extends App {
  implicit val system = ActorSystem("e01-basics")

  val api = system.actorOf(Props[MainActor], "main-routing-actor")

  IO(Http)(system) ! Http.Bind(listener = api, interface = "0.0.0.0", port = 8080)
}
