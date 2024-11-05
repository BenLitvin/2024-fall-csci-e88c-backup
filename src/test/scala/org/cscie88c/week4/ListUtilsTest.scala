package org.cscie88c.week4

import org.cscie88c.testutils.{StandardTest}

class ListUtilsTest extends StandardTest {
  "ListUtils" when {
    // Unit tests for 'ones'
    "calling ones" should {
      "return a list of ones with the correct size" in {
        val size = 5
        val result = ListUtils.ones(size)
        result should have size size // Ensure the list has the correct size
        all(result) should be(1.0) // All elements should be 1.0
      }

      "return an empty list when size is 0" in {
        val result = ListUtils.ones(0)
        result shouldBe empty // Ensure the list is empty
      }

      "return a list of one element when size is 1" in {
        val result = ListUtils.ones(1)
        result should have size 1
        all(result) should be(1.0) // Single element should be 1.0
      }
    }

    // Unit tests for 'zeroes'
    "calling zeroes" should {
      "return a list of zeroes with the correct size" in {
        val result = ListUtils.zeroes(5)
        result should have size 5
        all(result) should be(0.0) // All elements should be 0.0
      }

      "return an empty list when size is 0" in {
        val result = ListUtils.zeroes(0)
        result shouldBe empty
      }

      "return a list of one element when size is 1" in {
        val result = ListUtils.zeroes(1)
        result should have size 1
        all(result) should be(0.0) // Single element should be 0.0
      }
    }

    // Unit test for charCounts verifying the character frequencies for the string
    "calling charCounts" should {
      "return the correct character frequencies for 'Hello world'" in {
        val result = ListUtils.charCounts("Hello world")
        result should be(
          Map(
            'e' -> 1,
            'l' -> 3,
            'H' -> 1,
            'w' -> 1,
            'r' -> 1,
            'o' -> 2,
            'd' -> 1
          )
        )
      }
    }

    // Unit test for charCounts verifying that sentence is a pangram
    "verify that 'The quick brown fox jumps over the lazy dog' is a pangram" in {
      val result =
        ListUtils.charCounts("The quick brown fox jumps over the lazy dog")
      result.keys should contain allElementsOf "abcdefghijklmnopqrstuvwxyz".toSet
    }

    // Unit test for topN
    "calling topN" should {
      "return the top N characters by frequency" in {
        val frequencies = Map(
          'e' -> 1,
          'l' -> 3,
          'H' -> 1,
          'W' -> 1,
          'r' -> 1,
          'o' -> 2,
          'd' -> 1
        )
        val result = ListUtils.topN(2)(frequencies)
        result should be(List('l' -> 3, 'o' -> 2))
      }
    }

  }
}
