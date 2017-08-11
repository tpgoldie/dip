package com.tpg.dip

case class Band(lowerBound: BigDecimal, upperBound: BigDecimal) {
  def isIn(value: BigDecimal): Boolean = value.compare(lowerBound) >= 0 &&
    value.compare(upperBound) < 0
}
