package org.cscie88c.week4

object TrigUtils {

  // https://www.scala-lang.org/api/2.13.6/scala/math/index.html
  // use the function literal syntax for sin and cos
  val sinDegrees: Double => Double = (deg: Double) => math.sin(math.toRadians(deg))
  val cosDegrees: Double => Double = (deg: Double) => math.cos(math.toRadians(deg))


  // use the placeholder syntax for squared
  val squared: Double => Double = x => x * x
  
}
