package org.cscie88c.week2

import org.cscie88c.testutils.{StandardTest}

// write unit tests for Administrator below
class AdministratorTest extends StandardTest {

  "Administrator" when {
    "instantiated" should {
      // Test that the description contains the name
      "contain the name in the description" in {
        val admin = new Administrator(
          "Sherlock Holmes",
          "sherlock@221bbakerstreet.com",
          1500800
        )
        admin.description should include("Sherlock Holmes")
      }

      // Test that the description contains the email
      "contain the email in the description" in {
        val admin = new Administrator(
          "Albert Einstein",
          "einstein@relativity.com",
          2000000
        )
        admin.description should include("einstein@relativity.com")
      }

      // Test that the description contains the budget
      "contain the budget in the description" in {
        val admin = new Administrator(
          "Napoleon Bonaparte",
          "napoleon@france.gov",
          3000000
        )
        admin.description should include("$3000000")
      }

      // Test with a different Administrator instance
      "create new administrator instance and verify description" in {
        val admin =
          new Administrator("Isaac Newton", "newton@gravity.com", 4000000)
        admin.description should be(
          "Name: Isaac Newton, Email: newton@gravity.com, Budget: $4000000"
        )
      }
    }
  }
}
