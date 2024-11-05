package org.cscie88c.week3

object UtilFunctions {

  def mult2(x: Int, y: Int): Int = x * y

  def pythTest(x: Int, y: Int, z: Int): Boolean = {
    (x * x + y * y) == z * z
  }

  val pythTriplesUpto100: List[(Int, Int, Int)] = {
    (for {
      x <- 1 to 100
      y <-
        x to 100 // Ensure y >= x to avoid duplicates (to prevent redundancy and simplify the output)
      z <- y to 100 // Ensure z >= y to avoid duplicates
      if pythTest(x, y, z)
    } yield (x, y, z)).toList
  }

}
