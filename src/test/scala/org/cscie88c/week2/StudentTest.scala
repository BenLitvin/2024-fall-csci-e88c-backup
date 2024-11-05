package org.cscie88c.week2

import org.cscie88c.testutils.{StandardTest}

// write unit tests for Student below
class StudentTest extends StandardTest {

  "Student" when {

    // Test the CSV data constructor
    "created from a CSV string" should {
      "correctly parse the CSV and create a Student instance" in {
        val csv = "1,Emmy,Conrart,econrart0@gizmodo.com,Male,China"
        val student = Student(csv)
        student shouldBe Student(
          1,
          "Emmy",
          "Conrart",
          "econrart0@gizmodo.com",
          "Male",
          "China"
        )
      }
    }

    // Test studentNamesByCountry method
    "getting student names by country" should {
      "return a list of full names for students in China" in {
        val studentNames = Student.studentNamesByCountry("China")
        studentNames should contain allOf ("Emmy Conrart", "Jesse Chismon", "Jocelyn Blaxlande")
      }

      "return an empty list if no students are from the given country" in {
        val studentNames = Student.studentNamesByCountry("France")
        studentNames shouldBe empty
      }
    }

    // Test studentTotalsByCountry method
    "getting student totals by country" should {
      "return the correct total number of students in the United States" in {
        val totalStudents = Student.studentTotalsByCountry("United States")
        totalStudents shouldBe 2
      }

      "return zero if no students are from the given country" in {
        val totalStudents = Student.studentTotalsByCountry("Canada")
        totalStudents shouldBe 0
      }
    }
  }
}
