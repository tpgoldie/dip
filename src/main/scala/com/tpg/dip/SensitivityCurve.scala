package com.tpg.dip

import com.tpg.dip.Energy.Lambda

import scala.collection.immutable.HashMap

case class Sensitivity(value: Map[Lambda, (Sensor, Energy)]) {
  def *(lambdaSpectrums: Map[Location, LambdaSpectrum]): Map[Location, EnergySpectrum] = lambdaSpectrums.map(ls => (ls._1, this * ls._2))

  def *(lambdaSpectrum: LambdaSpectrum): EnergySpectrum = EnergySpectrum(lambdaSpectrum, this)

  def sensor(key: Lambda): Option[Sensor] = if (value.contains(key)) { Some(value(key)._1) } else { None }

  def energy(key: Lambda): Option[Energy] = if (value.contains(key)) { Some(value(key)._2) } else { None }
}

case class SensitivityCurve(sensitivities: Map[Lambda, (RGB, Energy)]) {
  def sensitivity(sensor: Sensor): Sensitivity = {
    sensor match {
      case r: R => Sensitivity(sensitivities.map(entry => (entry._1, (entry._2._1.r, entry._2._2))))
      case g: G => Sensitivity(sensitivities.map(entry => (entry._1, (entry._2._1.g, entry._2._2))))
      case b: B => Sensitivity(sensitivities.map(entry => (entry._1, (entry._2._1.b, entry._2._2))))

      case _ => Sensitivity(HashMap())
    }
  }
}
