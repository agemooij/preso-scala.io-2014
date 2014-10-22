package preso
package advanced
package dudes

import spray.http.StatusCodes._
import spray.httpx.SprayJsonSupport._
import spray.json.DefaultJsonProtocol._
import spray.routing._

trait DudesApiRoutes extends HttpService {
  val dude = Dude("agemooij", "Age Mooij", Set("Your humble presenter"))

  val dudesApiRoutes = {
    pathPrefix("dude") {
      pathEndOrSingleSlash {
        get {
          complete(OK, List(dude))
        } ~
          post {
            entity(as[Dude]) { dude ⇒
              complete(Created)
            }
          }
      } ~
        path(LongNumber) { id ⇒
          get {
            complete(OK, dude)
          }
        }
    }
  }
}
