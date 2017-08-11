package com.tpg.dip

import org.scalatest.{FlatSpec, Matchers}

class BandSpec extends FlatSpec with Matchers {
  "band" should "return true when value is within band" in {
    val actual = Band(BigDecimal(0.0), BigDecimal(1.0)).isIn(0.7)
    actual should be(true)
  }

  "band" should "return false when value is below the band" in {
    val actual = Band(BigDecimal(0.0), BigDecimal(1.0)).isIn(-0.7)
    actual should be(false)
  }

  "band" should "return false when value is above the band" in {
    val actual = Band(BigDecimal(0.0), BigDecimal(1.0)).isIn(1.7)
    actual should be(false)
  }
}
