package org.cscie88c.week2

import org.cscie88c.testutils.{StandardTest}

// write unit tests for Subject below
class SubjectTest extends StandardTest {

  "Subject" when {

    // Test the CSV data constructor
    "created from a CSV string" should {
      "correctly parse the CSV and create a Subject instance" in {
        val csv = "1,Physics,true"
        val subject = Subject(csv)
        subject shouldBe Subject(1, "Physics", true)
      }
    }

    // Test the stemSubjects method
    "getting STEM subjects" should {
      "return a list of all STEM subjects" in {
        val stemSubjects = Subject.stemSubjects
        stemSubjects should contain allOf (
          Subject(1, "Physics", true),
          Subject(2, "Chemistry", true),
          Subject(3, "Math", true)
        )
      }

      "not include non-STEM subjects" in {
        val stemSubjects = Subject.stemSubjects
        stemSubjects should not contain (Subject(4, "English", false))
      }
    }
  }
}
