package org.cscie88c.week2

import org.cscie88c.testutils.{StandardTest}

// write unit tests for Faculty below
class FacultyTest extends StandardTest {

  "Faculty" when {
    "instantiated" should {
      // Test that the description contains the name
      "contain the name in the description" in {
        val faculty = new Faculty(
          "Sherlock Holmes",
          "sherlock@221bbakerstreet.com",
          "Detective101"
        )
        faculty.description should include("Sherlock Holmes")
      }

      // Test that the description contains the email
      "contain the email in the description" in {
        val faculty = new Faculty(
          "Albert Einstein",
          "einstein@relativity.com",
          "Physics202"
        )
        faculty.description should include("einstein@relativity.com")
      }

      // Test that the description contains the courseId
      "contain the courseId in the description" in {
        val faculty = new Faculty(
          "Napoleon Bonaparte",
          "napoleon@france.gov",
          "WarHistory303"
        )
        faculty.description should include("WarHistory303")
      }

      // Test with a different Faculty instance
      "create a new faculty instance and verify description" in {
        val faculty =
          new Faculty("Isaac Newton", "newton@gravity.com", "Math404")
        faculty.description should be(
          "Name: Isaac Newton, Email: newton@gravity.com, Course: Math404"
        )
      }
    }
  }
}
