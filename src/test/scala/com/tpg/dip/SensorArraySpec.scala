package com.tpg.dip

import com.tpg.dip.Energy.Lambdas
import org.scalatest.{FeatureSpec, GivenWhenThen, Matchers}

import scala.collection.immutable.HashMap

class SensorArraySpec extends FeatureSpec with Matchers with GivenWhenThen {
  info("sensor array")

  Feature("generating the sensor array") {
    Scenario("valid inputs") {
      Given("the wavelengths of the photons that reach the pixel locations")
      And("the sensitivity curve")

      val wavelengths: Seq[(Location, Lambdas)] = Vector(
        ((1, 1), "0998781011"),
        ((1, 2), "1334452645"),
        ((1, 3), "6770566159"),
        ((2, 1), "0102114331"),
        ((2, 2), "3343445294"),
        ((2, 3), "7767615987"),
        ((3, 1), "6618789987"),
        ((3, 2), "0434154021"),
        ((3, 3), "3410042524")
      ).map(entry => (Location(entry._1), entry._2))

      val sensitivities = HashMap(
        '0' -> (RGB(0.0, 0.0, 0.2), Energy(1.0)),
        '1' -> (RGB(0.1, 0.2, 0.4), Energy(0.95)),
        '2' -> (RGB(0.2, 0.3, 0.8), Energy(0.9)),
        '3' -> (RGB(0.2, 0.4, 1.0), Energy(0.88)),
        '4' -> (RGB(0.3, 0.6, 0.7), Energy(0.85)),
        '5' -> (RGB(0.5, 1.0, 0.2), Energy(0.81)),
        '6' -> (RGB(0.6, 0.8, 0.1), Energy(0.78)),
        '7' -> (RGB(0.8, 0.6, 0.0), Energy(0.70)),
        '8' -> (RGB(1.0, 0.3, 0.0), Energy(0.60)),
        '9' -> (RGB(0.6, 0.0, 0.0), Energy(0.50))
      )

      val sensitivityCurve = SensitivityCurve(sensitivities)

      When("generating the sensor array")
      val sensorArray = SensorArray(3, sensitivityCurve, wavelengths)

      Then("the RGB values are calculated")
      sensorArray.energies should be(
        Map(
          Location(1, 1) -> RGB(2.645, BigDecimal("1.350"), BigDecimal("1.540")),
          Location(1, 2) -> RGB(BigDecimal("2.670"), 4.938, 5.047),
          Location(1, 3) -> RGB(3.729, 4.522, 1.138),
          Location(2, 1) -> RGB(1.167, 2.244, 4.995),
          Location(2, 2) -> RGB(2.433, 4.176, 5.902),
          Location(2, 3) -> RGB(4.576, 4.108, 0.698),
          Location(3, 1) -> RGB(4.551, 2.818, 0.536),
          Location(3, 2) -> RGB(1.716, 3.342, 4.707),
          Location(3, 3) -> RGB(1.801, 3.422, 5.047)
        ))
    }
  }
}
