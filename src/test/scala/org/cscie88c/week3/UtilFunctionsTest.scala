package org.cscie88c.week3

import org.cscie88c.testutils.{StandardTest}

class UtilFunctionsTest extends StandardTest {
  "UtilFunctions" when {
    "with pythTriplesUpto100" should {
      "verify elements in list are pythagorean triples" in {
        val triples = UtilFunctions.pythTriplesUpto100
        // Examples that satisfy the Pythagorean theorem (e.g., 3^2 + 4^2 = 9 + 16 = 25, which equals 5^2, etc.)
        triples should contain allOf ((3, 4, 5), (5, 12, 13), (7, 24, 25))

        // Check that the first element satisfies the Pythagorean triplet equation
        val (x, y, z) = triples.head
        UtilFunctions.pythTest(x, y, z) should be(true)
      }
    }
  }
}
