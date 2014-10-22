package preso
package basics

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

    "reject a PUT on /api/things/12345 with a MethodRejection" in {
      Put("/api/things/12345", Thing("thong")) ~> apiRoutes ~> check {
        handled should be(false)
        rejections should not be ('empty)
        rejection should be(a[MethodRejection])
      }
    }

    "reject a GET on /api/unsupported with an empty rejections list (i.e. a 404)" in {
      Get("/api/unsupported") ~> apiRoutes ~> check {
        handled should be(false)
        rejections should be('empty)
      }
    }
  }
}

