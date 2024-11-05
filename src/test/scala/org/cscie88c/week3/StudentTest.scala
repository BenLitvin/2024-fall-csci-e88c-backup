package org.cscie88c.week3

import org.cscie88c.testutils.{StandardTest}

class StudentTest extends StandardTest {
  "Student Management System" when {
    "creating a student" should {
      "have properties - name, email, subject and score" in {
        val student = Student("Emmy", "econrart0@gizmodo.com", "Math", 95)
        student.name should be("Emmy")
        student.email should be("econrart0@gizmodo.com")
        student.subject should be("Math")
        student.score should be(95)
      }
    }

    // Test for email validation
    "validating email addresses" should {
      "return true for valid email addresses" in {
        val student = Student("Jesse", "jchismon2@hostgator.com", "Physics", 90)
        Student.validateEmail(student) should be(true)
      }

      "return false for invalid email addresses" in {
        val student = Student(
          "Marin",
          "mblasoni1-edublogs.org",
          "Math",
          80
        ) // if missing '@'
        Student.validateEmail(student) should be(false)
      }
    }

    // Test for average score by subject
    "calculating average score by subject" should {
      "return the correct average for students enrolled in Math" in {
        val studentList = List(
          Student("Emmy", "econrart0@gizmodo.com", "Math", 95),
          Student("Jocelyn", "jblaxlande4@europa.eu", "Math", 85)
        )
        val averageScore = Student.averageScoreBySubject("Math", studentList)
        averageScore should be(90.0)
      }

      "return 0.0 if no students are enrolled in the given subject" in {
        val studentList = List(
          Student("Jesse", "jchismon2@hostgator.com", "Physics", 90)
        )
        val averageScore = Student.averageScoreBySubject("Math", studentList)
        averageScore should be(0.0)
      }
    }

    // Test for average score by student
    "calculating average score by student" should {
      "return the correct average score for a given student" in {
        val studentList = List(
          Student("Emmy", "econrart0@gizmodo.com", "Math", 95),
          Student("Emmy", "econrart0@gizmodo.com", "Physics", 85),
          Student("Jesse", "jchismon2@hostgator.com", "Math", 90)
        )
        val student = Student("Emmy", "econrart0@gizmodo.com", "Math", 95)
        val averageScore = Student.averageScoreByStudent(student, studentList)
        averageScore should be(90.0)
      }

      "return 0.0 if the student is not in the list" in {
        val studentList = List(
          Student("Jesse", "jchismon2@hostgator.com", "Math", 90)
        )
        val student = Student("Marin", "mblasoni1@edublogs.org", "Math", 80)
        val averageScore = Student.averageScoreByStudent(student, studentList)
        averageScore should be(0.0)
      }
    }
  }
}
