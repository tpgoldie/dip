package com.tpg.dip

import com.tpg.dip.Energy.Lambdas

case class SensorArray(size: Int, sensitivityCurve: SensitivityCurve, wavelengths: Seq[(Location, Lambdas)]) {
  private val lambdaSpectrums: Map[Location, LambdaSpectrum] = wavelengths.map(w => (w._1, LambdaSpectrum(w._2))).toMap

  private def g(sensor: Sensor, location: Location): Sensor = {
    val energySpectrums = sensitivityCurve.sensitivity(sensor) * lambdaSpectrums

    val energies = energySpectrums.map(es => (es._1, es._2.sum))
    val energy = energies.getOrElse(location, Energy.ZERO)

    sensor match {
      case r: R => R(energy.value)
      case g: G => G(energy.value)
      case b: B => B(energy.value)

      case _ => Zero
    }
  }

  def energies: Map[Location, RGB] = {
    val rZero = R(0.0)
    val gZero = G(0.0)
    val bZero = B(0.0)

    lambdaSpectrums.keySet.map(location => (location, RGB(
      g(rZero, location).asInstanceOf[R],
      g(gZero, location).asInstanceOf[G],
      g(bZero, location).asInstanceOf[B]
    ))).toMap
  }
}
