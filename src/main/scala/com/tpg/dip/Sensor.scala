package com.tpg.dip

sealed trait Sensor {
  def value: BigDecimal
}

case class B(value: BigDecimal) extends Sensor {}

case class G(value: BigDecimal) extends Sensor {}

case class R(value: BigDecimal) extends Sensor {}
