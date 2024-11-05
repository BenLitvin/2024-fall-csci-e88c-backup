package org.cscie88c.week3
import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers
import org.scalatestplus.scalacheck.ScalaCheckPropertyChecks
import org.scalacheck._

class StudentPropertyTest
    extends AnyFunSuite
    with Matchers
    with ScalaCheckPropertyChecks {

  val studentGen: Gen[Student] = for {
    name <- Gen.oneOf("Emmy", "Jesse", "Marin", "Jocelyn")
    email <- Gen.oneOf(
      "econrart0@gizmodo.com",
      "jchismon2@hostgator.com",
      "mblasoni1@edublogs.org",
      "jblaxlande4@europa.eu"
    )
    subject <- Gen.oneOf("Math", "Physics", "Science", "English")
    score <- Gen.choose(0, 100)
  } yield Student(name, email, subject, score)

  // BONUS - YAY! Student list generator
  val studentListGen: Gen[List[Student]] = Gen.listOf(studentGen)

  // Test to verify that the student description contains name and email
  test("description contains name and email") {
    forAll(studentGen) { student =>
      student.description should include(student.name)
      student.description should include(student.email)
    }
  }
  
  // Bonus 2: Test to verify average score for Math is less than 100
  test("averageScoreBySubject for Math should be less than 100") {
    forAll(studentListGen) { students =>
      val mathAverageScore = Student.averageScoreBySubject("Math", students)
      mathAverageScore should be < 100.0
    }
  }
}
