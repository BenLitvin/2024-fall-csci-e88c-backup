package org.cscie88c.week5

import org.cscie88c.testutils.{StandardTest}
import Streams.Dog

class StreamsTest extends StandardTest {
  "calling healthyDogs" should {
    "return only healthy dogs in the stream" in {
      val sampleDogs =
        Streams.dogs.take(5) // Take a sample of 5 dogs from the infinite stream
      val healthyDogsSample = Streams.healthyDogs(
        sampleDogs
      ) // Filter the sample to return only healthy dogs

      healthyDogsSample should not be empty // Ensure that there are some healthy dogs
      healthyDogsSample.foreach { dog =>
        dog.hasCurrentShots shouldBe true // Assert that every dog in the filtered sample has its shot
      }
    }
  }

  "calling averageHealthyAge" should {
    "return the average age of healthy dogs from a sample size" in {
      val sampleSize = 5
      val averageAge = Streams.averageHealthyAge(
        Streams.dogs.take(sampleSize), // Take a limited sample of 5 dogs
        sampleSize
      )
      averageAge should (be >= 0.0 and be <= 15.0) // Ensure the average age is between 0 and 15
    }

    "return 0.0 if no healthy dogs are found" in {
      val unhealthyDogs = LazyList
        .continually(
          Streams.Dog("unhealthy-dog", 5, false)
        ) // Create a stream of unhealthy dogs
        .take(5)

      Streams.averageHealthyAge(
        unhealthyDogs,
        5
      ) shouldBe 0.0 // Assert that the result is 0.0 if no healthy dogs are found
    }
  }
}
