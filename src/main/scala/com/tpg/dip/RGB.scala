package com.tpg.dip

case class RGB(r: R, g: G, b: B) {
}

object RGB {
  def apply(r: BigDecimal, g: BigDecimal, b: BigDecimal): RGB = new RGB(R(r), G(g), B(b))
}
