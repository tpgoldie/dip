package com.tpg.dip

import com.tpg.dip.Energy.{Lambda, Lambdas}

case class Energy(value: BigDecimal) {
  def *(input: Int): Energy = Energy(value * input)

  def *(sensor: Sensor): Energy = Energy(value * sensor.value)

  def + (that: Energy): Energy = Energy(value + that.value)
}

object Energy {
  type Lambda = Char
  type Lambdas = String

  val ZERO: Energy = Energy(BigDecimal(0.0))
}

case class LambdaSpectrum(spread: Seq[(Lambda, Int)]) {

  private def calculateEnergySpectra(entry: (Lambda, Int), sensitivity: Sensitivity): Energy = {
    sensitivity.energy(entry._1).getOrElse(Energy.ZERO) * entry._2 * sensitivity.sensor(entry._1).getOrElse(Zero)
  }

  def *(sensitivity: Sensitivity): Map[Lambda, Energy] = {
    spread.map(entry => (entry._1, calculateEnergySpectra(entry, sensitivity))).toMap
  }
}

object LambdaSpectrum {
  def apply(lambdas: Lambdas): LambdaSpectrum = LambdaSpectrum(lambdas.groupBy(identity).mapValues(_.length).toSeq.sortBy(_._1))
}


case class EnergySpectrum(lambdaSpectrum: LambdaSpectrum, sensitivity: Sensitivity) {
  val spectrum = lambdaSpectrum * sensitivity

  val sum: Energy = spectrum.values.foldLeft(Energy.ZERO)(_ + _)
}