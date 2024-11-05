package org.cscie88c.week2

import org.cscie88c.testutils.{StandardTest}

class UtilFunctionsTest extends StandardTest {

  "UtilFunctions" when {
    "maximum" should {
      "return maximum of two ints when first integer is greater" in {
        UtilFunctions.maximum(2, 1) should be(2)
      }
      "return maximum of two ints when second integer is greater" in {
        UtilFunctions.maximum(4, 5) should be(5)
      }
      "return either when both integers are equal" in {
        UtilFunctions.maximum(3, 3) should be(3)
      }
      "return maximum of two ints when the first is negative" in {
        UtilFunctions.maximum(-5, 10) should be(10)
      }
      "return maximum of two ints when the second is negative" in {
        UtilFunctions.maximum(5, -10) should be(5)
      }
      "return maximum of two ints when both are negative" in {
        UtilFunctions.maximum(-5, -10) should be(-5)
      }
    }

    "average" should {
      "return average of two positive integers" in {
        UtilFunctions.average(1, 6) should be(3.5)
      }
      "return average when both integers are the same" in {
        UtilFunctions.average(3, 3) should be(3.0)
      }
      "return average of a positive and a negative integer" in {
        UtilFunctions.average(6, -4) should be(1.0)
      }
      "return average of two negative integers" in {
        UtilFunctions.average(-6, -4) should be(-5.0)
      }
    }
  }
}
