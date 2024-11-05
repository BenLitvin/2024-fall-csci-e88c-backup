package org.cscie88c.week5

import org.cscie88c.testutils.{StandardTest}
import FunctionUtils.CustomerTransaction
import org.cscie88c.week5.FunctionUtils.tanDegrees

// run using: sbt "testOnly org.cscie88c.week5.FunctionUtilsTest"
class FunctionUtilsTest extends StandardTest {
  "FunctionUtils" when {
    // Problem 1 unit tests
    "calling colorToCode" should {
      "return the correct value for white" in {
        FunctionUtils.colorToCode("White") shouldBe (255, 255, 255)
      }
      "return the correct value for lime" in {
        FunctionUtils.colorToCode("lime") shouldBe (0, 255, 0)
      }
    }

    "calling fizzBuzzString" should {
      "return FizzBuzz for numbers divisible by 3 and 5 both" in {
        FunctionUtils.fizzBuzzString(15) shouldBe "FizzBuzz"
        FunctionUtils.fizzBuzzString(30) shouldBe "FizzBuzz"
      }

      "return Fizz for numbers divisible by 3" in {
        FunctionUtils.fizzBuzzString(9) shouldBe "Fizz"
        FunctionUtils.fizzBuzzString(12) shouldBe "Fizz"
      }

      "return Buzz for numbers divisible by 5" in {
        FunctionUtils.fizzBuzzString(10) shouldBe "Buzz"
        FunctionUtils.fizzBuzzString(20) shouldBe "Buzz"
      }

      "return the numeric velue as a string for all other numbers" in {
        FunctionUtils.fizzBuzzString(7) shouldBe "7"
        FunctionUtils.fizzBuzzString(16) shouldBe "16"
      }
    }

    "calling fizzBuzz" should {
      "return the correct value" in {
        FunctionUtils.fizzBuzz(1) shouldBe List("1")
        FunctionUtils.fizzBuzz(3) shouldBe List("1", "2", "Fizz")
        FunctionUtils.fizzBuzz(5) shouldBe List("1", "2", "Fizz", "4", "Buzz")
        FunctionUtils
          .fizzBuzz(6) shouldBe List("1", "2", "Fizz", "4", "Buzz", "Fizz")
        FunctionUtils.fizzBuzz(10) shouldBe List(
          "1",
          "2",
          "Fizz",
          "4",
          "Buzz",
          "Fizz",
          "7",
          "8",
          "Fizz",
          "Buzz"
        )
        FunctionUtils.fizzBuzz(15) shouldBe List(
          "1",
          "2",
          "Fizz",
          "4",
          "Buzz",
          "Fizz",
          "7",
          "8",
          "Fizz",
          "Buzz",
          "11",
          "Fizz",
          "13",
          "14",
          "FizzBuzz"
        )
      }
    }

    // Problem 2 unit tests
    "calling tanDegrees" should {
      "throw error for angles -90 and 90" in {
        tanDegrees.isDefinedAt(90) shouldBe false
        tanDegrees.isDefinedAt(-90) shouldBe false
      }

      "return correct tan for valid angles" in {
        tanDegrees(0) shouldBe 0.0
        tanDegrees(45) shouldBe scala.math.tan(
          scala.math.Pi / 4
        )
      }
    }

    "calling totalHighValueTransactions" should {
      "return the sum of transactions greater than 100" in {
        val transactions = List(
          CustomerTransaction("Bill Gates", "10/08/2024", 50),
          CustomerTransaction("Jeff Bezos", "10/08/2024", 150),
          CustomerTransaction("Elon Musk", "10/08/2024", 250)
        )
        FunctionUtils.totalHighValueTransactions(transactions) shouldBe 400.0
      }

      "return 0.0 if there are no high value transactions" in {
        val transactions = List(
          CustomerTransaction("Cheap Charlie", "10/08/2024", 50),
          CustomerTransaction("Broke Bob", "10/08/2024", 80)
        )
        FunctionUtils.totalHighValueTransactions(transactions) shouldBe 0.0
      }
    }

    // Problem 3 unit tests
    "calling flip2" should {
      "flip the order of arguments to the function" in {
        val flippedPow = FunctionUtils.flip2(math.pow)
        flippedPow(5, 2) shouldBe 32.0 // 2^5 = 32.0
      }
    }

    "calling sampleList" should {
      "return the first 5 elements of an Int list" in {
        FunctionUtils.sampleList(List(1, 2, 3, 4, 5, 6, 7)) shouldBe List(1, 2,
          3, 4, 5)
      }

      "return the first 5 elements of a String list" in {
        FunctionUtils.sampleList(
          List("one", "two", "three", "four", "five", "six", "seven")
        ) shouldBe List("one", "two", "three", "four", "five")
      }

      "return the entire list if it has less than 5 elements" in {
        FunctionUtils.sampleList(List(1, 2, 3)) shouldBe List(1, 2, 3)
        FunctionUtils.sampleList(List("one", "two")) shouldBe List("one", "two")
      }
    }
  }
}
