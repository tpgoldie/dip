package com.tpg.dip

import com.tpg.dip.Energy.Lambda
import org.scalatest.{FeatureSpec, GivenWhenThen, Matchers}

import scala.collection.immutable.HashMap

class SensorArraySpec extends FeatureSpec with Matchers with GivenWhenThen {
  info("sensor array")

  Feature("generating the sensor array") {
    Scenario("valid inputs") {
      Given("the wavelengths of the photons that reach the pixel locations")
      And("the sensitivity curve")

      val wavelengths: Seq[(Location, Lambda)] = Vector(
        ((1, 1), "0998781011"),
        ((1, 2), "1334452645"),
        ((1, 3), "6770566159"),
        ((2, 1), "0102114331"),
        ((2, 2), "3343445294"),
        ((2, 3), "7767615987"),
        ((3, 1), "6618789987"),
        ((3, 2), "0434154021"),
        ((3, 3), "3410042524")
      ).map(entry => (Location(entry._1), entry._2.toCharArray))

      val sensitivities = HashMap(
        "0" -> (RGB(R(0.0), G(0.0), B(0.2)), Energy(1.0)),
        "1" -> (RGB(R(0.1), G(0.2), B(0.4)), Energy(0.95)),
        "2" -> (RGB(R(0.2), G(0.3), B(0.8)), Energy(0.9)),
        "3" -> (RGB(R(0.2), G(0.4), B(1.0)), Energy(0.9)),
        "4" -> (RGB(R(0.3), G(0.6), B(0.7)), Energy(0.9)),
        "5" -> (RGB(R(0.5), G(1.0), B(0.2)), Energy(0.9)),
        "6" -> (RGB(R(0.6), G(0.8), B(0.1)), Energy(0.9)),
        "7" -> (RGB(R(0.8), G(0.6), B(0.0)), Energy(0.9)),
        "8" -> (RGB(R(1.0), G(0.3), B(0.0)), Energy(0.9)),
        "9" -> (RGB(R(0.6), G(0.0), B(0.0)), Energy(0.9))
      )

      val sensitivityCurve = SensitivityCurve(sensitivities)

      val sensorArray = SensorArray(3, sensitivityCurve, wavelengths)
    }
  }
}
