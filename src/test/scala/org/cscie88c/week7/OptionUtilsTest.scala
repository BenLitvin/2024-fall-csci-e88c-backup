package org.cscie88c.week7

import org.cscie88c.testutils.StandardTest
import scala.util.{Try, Success, Failure}

class OptionUtilsTest extends StandardTest {
  "OptionUtils" when {

    "calling fileCharCount" should {

      "return the correct number of characters in data/dirty-retail-data-sample.csv" in {
        val result =
          OptionUtils.fileCharCount("data/dirty-retail-data-sample.csv")
        result shouldBe a[Success[_]]
        result.get shouldBe 195L
      }

      "return a Failure when the file does not exist" in {
        val result = OptionUtils.fileCharCount("NotExistingFile.csv")
        result shouldBe a[Failure[_]]
      }
    }

    "calling charCountAsString" should {

      "return the correct string for the file data/dirty-retail-data-sample.csv" in {
        val result =
          OptionUtils.charCountAsString("data/dirty-retail-data-sample.csv")
        result shouldBe "number of characters: 195"
      }

      "return an error string for the non-existing file data/dirty-retail-data-sample.csv-x" in {
        val result =
          OptionUtils.charCountAsString("data/dirty-retail-data-sample.csv-x")
        result shouldBe "error opening file"
      }
    }
  }
}
