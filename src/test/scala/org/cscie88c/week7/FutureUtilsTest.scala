package org.cscie88c.week7

import org.cscie88c.testutils.{FuturesTest}
import scala.concurrent.Future

class FutureUtilsTest extends FuturesTest {

  "Any future function" should {
    "return a future assertion" in {
      def futureAdd2(x: Int) = Future(x + 2)
      futureAdd2(5).map { x =>
        x shouldBe 7
      }
    }
  }

  "FutureFunctions" when {

    // Task 3.3 Test for creditScoreAPI
    "calling creditScoreAPI" should {
      "return a credit score between 300 and 800" in {
        val result = FutureUtils.creditScoreAPI(123)

        result.map { score =>
          score should be >= 300
          score should be <= 800
        }
      }
    }

    // Task 3.5 Test for futureFactorial(4)
    "calling futureFactorial" should {
      "return Future(Success(24)) for futureFactorial(4)" in {
        val result = FutureUtils.futureFactorial(4)

        result.map { factorialResult =>
          factorialResult shouldBe 24
        }
      }
    }

    // Task 4.3 Test for futurePermutations
    "calling futurePermutations" should {
      "return the correct number of permutations for futurePermutations(5, 3)" in {
        val result = FutureUtils.futurePermutations(5, 3)

        // Result for P(5, 3) = 5! / (5 - 3)! = 120 / 2 = 60
        result.map { permutationsResult =>
          permutationsResult shouldBe 60
        }
      }
    }

    // Task 4.5: Test for asyncAverageCreditScore
    "calling asyncAverageCreditScore" should {
      "calculate an average credit score greater than 300 and less than 800" in {
        val result = FutureUtils.asyncAverageCreditScore(List(1, 2, 3))

        result.map { avgScore =>
          avgScore should be > 300.0
          avgScore should be < 800.0
        }
      }
    }

    // Bonus Task 1. Test for slowMultiplication
    "calling slowMultiplication" should {
      "return the correct product after 1 second delay" in {
        val startTime = System.currentTimeMillis()

        // slowMultiplication(3, 4) should return 12 after a delay
        val result = FutureUtils.slowMultiplication(3, 4)
        result shouldBe 12

        val endTime = System.currentTimeMillis()
        val duration = endTime - startTime

        // Check that the operation took at least 1 second (1000 ms)
        duration should be >= 1000L
      }
    }

    // Bonus Task 2. Test for sequentialFactorial
    "calling sequentialFactorial" should {
      "calculate the correct factorial sequentially" in {
        val startTime = System.currentTimeMillis()

        val result = FutureUtils.sequentialFactorial(5)
        result shouldBe 120 // 5! = 120

        val endTime = System.currentTimeMillis()
        val duration = endTime - startTime

        // Here is better to give more flexibility in timing
        duration should be >= 4000L // It should take about 4-5 seconds
        duration should be < 6000L  // If we account minor system overhead
      }
    }

    // Bonus Task 2. Test for concurrentFactorial
    "calling concurrentFactorial" should {
      "calculate the correct factorial concurrently" in {
        val startTime = System.currentTimeMillis()

        val result = FutureUtils.concurrentFactorial(5)
        result shouldBe 120 // 5! = 120

        val endTime = System.currentTimeMillis()
        val duration = endTime - startTime

        // Increased the flexibility in timing for concurrent execution
        duration should be < 3500L
      }
    }

    // Extra Test for larger n (to observe time differences)
    "calling concurrentFactorial with larger n" should {
      "calculate the correct factorial concurrently for n = 10 and be faster" in {
        val startTime = System.currentTimeMillis()

        val result = FutureUtils.concurrentFactorial(10)
        result shouldBe 3628800 // 10! = 3628800

        val endTime = System.currentTimeMillis()
        val duration = endTime - startTime

        // We expect the concurrent computation to be faster even for larger n
        duration should be < 6000L
      }
    }
  }
}