package org.cscie88c.week2

import org.cscie88c.testutils.{StandardTest}

class UniversityEmployeeTest extends StandardTest {

  "UniversityEmployee" when {
    "instantiated" should {
      // Test that the employee has a name property
      "have a name property" in {
        val employee = new UniversityEmployee(
          "Sherlock Holmes",
          "sherlock@221bbakerstreet.com"
        )
        employee.name should be("Sherlock Holmes")
      }

      // Test that the employee has an email property
      "have an email property" in {
        val employee =
          new UniversityEmployee("Albert Einstein", "einstein@relativity.com")
        employee.email should be("einstein@relativity.com")
      }

      // Test that the description contains the name
      "have a description that contains the name" in {
        val employee =
          new UniversityEmployee("Napoleon Bonaparte", "napoleon@france.gov")
        employee.description should include("Napoleon Bonaparte")
      }

      // Test that the description contains the email
      "have a description that contains the email" in {
        val employee =
          new UniversityEmployee("Charlie Chaplin", "chaplin@silentmovies.com")
        employee.description should include("chaplin@silentmovies.com")
      }

      // Test with a different employee instance
      "create a new employee instance and verify description" in {
        val employee =
          new UniversityEmployee("Isaac Newton", "newton@gravity.com")
        employee.description should be(
          "Name: Isaac Newton, Email: newton@gravity.com"
        )
      }
    }
  }
}
