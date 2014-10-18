package preso.e01.basics

import akka.actor._
import spray.routing._

import auth._
import common._
import rfs.rebb.exacttarget._
import rfs.rebb.product._
import rfs.rebb.search._
import rfs.rebb.taxonomy.categories._
import salesforce._
import shopper._

class MainActor extends HttpServiceActor
    with ActorLogging
    with ActorRefFactoryExecutionContextProvider
    with ActorCreationSupportForActors {

  def receive = runRoute(routes)
}

trait Routes extends HttpService {
  // format: OFF
  val routes =

  // format: ON
}
