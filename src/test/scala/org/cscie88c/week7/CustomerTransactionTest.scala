package org.cscie88c.week7

import org.cscie88c.testutils.{StandardTest}
import scala.util.{Try, Success, Failure}

class CustomerTransactionTest extends StandardTest {
  "CustomerTransaction" should {
    // Task 2.2
    "load and clean raw CSV data file" in {
      // Test for a valid CSV input
      val validResult = CustomerTransaction("id1,customer-A,84.5")
      validResult shouldBe Some(CustomerTransaction("id1", "customer-A", 84.5))

      // Test for invalid CSV input with non-numeric transaction amount
      val invalidAmountResult =
        CustomerTransaction("id1,customer-A,invalidAmount")
      invalidAmountResult shouldBe None

      // Test for invalid CSV input with missing fields
      val missingFieldsResult =
        CustomerTransaction("id1,customer-A") // no transactionAmount
      missingFieldsResult shouldBe None

      // Test for invalid CSV input with extra fields
      val extraFieldsResult =
        CustomerTransaction("id1,customer-A,84.5,extraField")
      extraFieldsResult shouldBe None

      // Test for empty CSV string
      val emptyStringResult = CustomerTransaction("")
      emptyStringResult shouldBe None

      // Test for CSV string with only commas
      val commasOnlyResult = CustomerTransaction(",,")
      commasOnlyResult shouldBe None
    }
    // Task 2.4
    "read 5 valid customer records from the file" in {
      val result =
        CustomerTransaction.readFromCSVFile("data/dirty-retail-data-sample.csv")
      result.length shouldBe 5

      // Here we check that the records match expected values from the file
      result should contain theSameElementsAs List(
        CustomerTransaction("CS5295", "11-Feb-13", 35.0),
        CustomerTransaction("CS4768", "15-Mar-15", 39.0),
        CustomerTransaction("CS1217", "16-Nov-11", 99.0),
        CustomerTransaction("CS1850", "20-Nov-13", 78.0),
        CustomerTransaction("CS2724", "06-Feb-12", 93.0)
      )
    }
  }
}
