package preso
package e01.basics

import spray.http._
import spray.httpx.SprayJsonSupport._
import spray.json.DefaultJsonProtocol._
import spray.routing._

import org.scalatest._
import spray.testkit._

class ApiRoutesSpec extends ApiRoutes with WordSpecLike with Matchers with Inspectors with ScalatestRouteTest {
  def actorRefFactory = system

  "The Spray Api route handler" should {
    "handle a GET on /api/things by returning some things in JSON format" in {
      Get("/api/things") ~> apiRoutes ~> check {
        status shouldBe StatusCodes.OK
        contentType shouldBe ContentTypes.`application/json`
        responseAs[List[Thing]] should have size (2)
      }
    }

    "handle a POST on /api/things by responding with Created in JSON format" in {
      Post("/api/things", Thing("thong")) ~> apiRoutes ~> check {
        status shouldBe StatusCodes.Created
        contentType shouldBe ContentTypes.`text/plain(UTF-8)`
      }
    }

    "handle a GET on /api/things/12345 by returning one thing in JSON format" in {
      Get("/api/things/12345") ~> apiRoutes ~> check {
        status shouldBe StatusCodes.OK
        contentType shouldBe ContentTypes.`application/json`
        responseAs[Thing].name should include("12345")
      }
    }
  }
}












