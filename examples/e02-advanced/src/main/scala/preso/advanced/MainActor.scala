package preso
package advanced

import akka.actor._
import spray.http.StatusCodes._
import spray.httpx.SprayJsonSupport._
import spray.json.DefaultJsonProtocol._
import spray.routing._

import util._

class MainActor extends HttpServiceActor with ActorLogging with Routes {
  def receive = runRoute(routes)
}

trait Routes extends ApiRoutes with AssetRoutes with HttpsDirectives with SettingsProvider {
  val routes = {
    (decompressRequest() & compressResponseIfRequested(())) {
      enforceHttpsIf(settings.Http.EnforceHttps) {
        apiRoutes ~ assetRoutes
      }
    }
  }
}
