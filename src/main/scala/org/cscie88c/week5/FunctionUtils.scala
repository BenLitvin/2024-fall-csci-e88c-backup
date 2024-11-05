package org.cscie88c.week5

import scala.math._

object FunctionUtils {

  case class CustomerTransaction(
      customerId: String,
      transactionDate: String,
      transactionAmount: Double
  )

  // Problem 1
  def colorToCode(color: String): (Int, Int, Int) = color.toLowerCase match {
    case "black"  => (0, 0, 0)
    case "white"  => (255, 255, 255)
    case "red"    => (255, 0, 0)
    case "lime"   => (0, 255, 0)
    case "blue"   => (0, 0, 255)
    case "yellow" => (255, 255, 0)
    case _ => throw new IllegalArgumentException(s"Unknown color: $color")
  }

  def fizzBuzzString(n: Int): String = n match {
    case _ if n % 3 == 0 && n % 5 == 0 => "FizzBuzz"
    case _ if n % 3 == 0               => "Fizz"
    case _ if n % 5 == 0               => "Buzz"
    case _                             => n.toString
  }

  def fizzBuzz(n: Int): List[String] = {
    if (n <= 0) {
      throw new IllegalArgumentException("n must be a positive integer")
    } else {
      (1 to n).toList.map(fizzBuzzString)
    }
  }

  // Problem 2
  def tanDegrees: PartialFunction[Double, Double] = {
    case x if x != 90 && x != -90 => tan(toRadians(x))
  }

  def totalHighValueTransactions(
      transactionList: List[CustomerTransaction]
  ): Double = {
    transactionList.collect {
      case CustomerTransaction(_, _, amount) if amount > 100 => amount
    }.sum
  }

  // Problem 3
  def flip2[A, B, C](f: (A, B) => C): (B, A) => C = { (b: B, a: A) =>
    f(a, b)
  }

  // Write a generic function sampleList parameterized by type A, that returns the first 5 elements of a list of type A.
  def sampleList[A](list: List[A]): List[A] = {
    list.take(5)
  }

}
