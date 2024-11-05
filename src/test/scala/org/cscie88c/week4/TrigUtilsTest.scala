package org.cscie88c.week4

import org.cscie88c.testutils.{StandardTest}

class TrigUtilsTest extends StandardTest {

  "TrigUtils" when {
    "calling sin" should {
      "return the correct value for 90 degrees" in {
        TrigUtils.sinDegrees(90) should be(1.0 +- 0.0001) // Sin of 90° is 1
      }

      "return the correct value for 0 degrees" in {
        TrigUtils.sinDegrees(0) should be(0.0 +- 0.0001) // Sin of 0° is 0
      }
    }

    "calling cos" should {
      "return the correct value for 0 degrees" in {
        TrigUtils.cosDegrees(0) should be(1.0 +- 0.0001) // Cos of 0° is 1
      }

      "return the correct value for 90 degrees" in {
        TrigUtils.cosDegrees(90) should be(0.0 +- 0.0001) // Cos of 90° is 0
      }
    }

    "calling squared" should {
      "return the square of positive numbers" in {
        TrigUtils.squared(2.0) should be(4.0)
        TrigUtils.squared(3.0) should be(9.0)
      }

      "return the square of negative numbers" in {
        TrigUtils.squared(-2.0) should be(4.0)
        TrigUtils.squared(-3.0) should be(9.0)
      }

      "return 0 when the input is 0" in {
        TrigUtils.squared(0.0) should be(0.0)
      }
    }
  }
}
