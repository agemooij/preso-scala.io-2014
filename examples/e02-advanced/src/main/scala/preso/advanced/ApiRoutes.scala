package preso
package advanced

import spray.http.CacheDirectives._
import spray.http.HttpHeaders._
import spray.http.StatusCodes._
import spray.httpx.SprayJsonSupport._
import spray.json.DefaultJsonProtocol._
import spray.routing._

import things._
import dudes._

trait ApiRoutes extends HttpService with ThingsApiRoutes with DudesApiRoutes {
  val ApiRejectionHandler = RejectionHandler.Default

  // format: OFF
  val apiRoutes = {
    pathPrefix("api") {
      handleRejections(ApiRejectionHandler) {
        respondWithHeaders(List(`Cache-Control`(`no-store`), RawHeader("Pragma", "no-cache"))) {
          thingsApiRoutes ~ dudesApiRoutes
        }
      }
    }
  }
  // format: ON
}
