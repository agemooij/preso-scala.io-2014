package preso
package e01.basics

import akka.actor._
import spray.http.StatusCodes._
import spray.httpx.SprayJsonSupport._
import spray.json.DefaultJsonProtocol._
import spray.routing._

class ApiRoutesActor extends HttpServiceActor with ActorLogging with ApiRoutes {
  def receive = runRoute(apiRoutes)
}

trait ApiRoutes extends HttpService {
  // format: OFF
  val apiRoutes = {
    pathPrefix("api") {
      pathPrefix("things") {
        pathEnd {
          get {
            complete(OK, List(Thing("Apple"), Thing("iPhone")))
          } ~
          post {
            entity(as[Thing]) { thing ⇒
              complete(Created)
            }
          }
        } ~
        path(LongNumber) { id =>
          get {
            complete(OK, Thing(s"Thing # $id"))
          }
        }
      }
    }
  }
  // format: ON
}
