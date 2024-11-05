package org.cscie88c.week4

import org.cscie88c.testutils.{StandardTest}

class FunctionUtilsTest extends StandardTest {

  "FunctionUtils" when {
    "calling applyNtimes" should {
      "return the correct value for adding 5 three times" in {
        // Testing applyNtimes with the add5 function (adds 5 to input)
        def add5(x: Int): Int = x + 5
        FunctionUtils.applyNtimes(3)(0)(add5) should be(
          15 // 0 + 5 + 5 + 5 = 15
        )
      }

      "return the correct value applying a function 0 times" in {
        // Testing that applying 0 times returns the initial input
        def add5(x: Int): Int = x + 5
        FunctionUtils.applyNtimes(0)(5)(add5) should be(
          5 // No changes, should be 5
        )
      }
    }

    "calling myPositivePower" should {
      "return the correct power for a base and exponent" in {
        // Testing power function for base and exponent
        FunctionUtils.myPositivePower(2, 3) should be(8) // 2^3 = 8
        FunctionUtils.myPositivePower(5, 0) should be(
          1 // Any number raised to 0 is 1
        )
      }
    }

    "calling deferredExecutor" should {
      "return the correct value and print the log statement" in {
        // Testing deferred executor with logging
        def add5(x: Int): Int = x + 5
        val myDeferredFunction =
          FunctionUtils.deferredExecutor("CPU Pool")(add5)
        val result = myDeferredFunction(4)
        result should be(9) // 4 + 5 = 9
      }
    }
  }
}
