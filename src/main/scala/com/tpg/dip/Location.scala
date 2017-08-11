package com.tpg.dip

case class Location(x: Int, y: Int) {
}

object Location {
  def apply(entry: (Int, Int)): Location = Location(entry._1, entry._2)
}
