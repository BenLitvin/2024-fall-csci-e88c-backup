package org.cscie88c.week3

import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers
import org.scalatestplus.scalacheck.ScalaCheckPropertyChecks
import org.scalacheck._

class UtilFunctionsPropertyTest
    extends AnyFunSuite
    with Matchers
    with ScalaCheckPropertyChecks {

  test("Distributive property of multiplication") {
    forAll { (a: Int, b: Int, c: Int) =>
      UtilFunctions.mult2(a, b + c) shouldBe UtilFunctions.mult2(
        a,
        b
      ) + UtilFunctions.mult2(a, c)
    }
  }

  val triplesGen: Gen[(Int, Int, Int)] =
    Gen.oneOf(UtilFunctions.pythTriplesUpto100)

  test("mult2 result test") {
    forAll { (x: Int, y: Int) =>
      UtilFunctions.mult2(x, y) shouldBe x * y
    }
  }

  // Testing reversals of first two (if we swap the first two numbers in a triple, it should still form a valid triple)
  test("reversal of first two should be valid") {
    forAll(triplesGen) { case (x, y, z) =>
      UtilFunctions.pythTest(y, x, z) shouldBe true
    }
  }

  // Bounus 1: Pythagorean triple by 2
  test("If (x, y, z) is a Pythagorean triple, then (2y, 2x, 2z) should also be a Pythagorean triple") {
    forAll(triplesGen) { case (x, y, z) =>
      val scaledX = 2 * x
      val scaledY = 2 * y
      val scaledZ = 2 * z
      UtilFunctions.pythTest(scaledX, scaledY, scaledZ) shouldBe true
    }
  }
}
