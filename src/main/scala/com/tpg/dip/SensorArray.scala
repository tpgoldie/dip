package com.tpg.dip

import com.tpg.dip.Energy.Lambda

case class SensorArray(size: Int, sensitivityCurve: SensitivityCurve, wavelengths: Seq[(Location, Lambda)]) {
  val array = ???
}
