package org.cscie88c.week2

final case class Student(
    id: Int,
    firstName: String,
    lastName: String,
    email: String,
    gender: String,
    country: String
)

object Student {

  // List of all students
  val allStudents: List[Student] = List(
    Student(1, "Emmy", "Conrart", "econrart0@gizmodo.com", "Male", "China"),
    Student(
      2,
      "Marin",
      "Blasoni",
      "mblasoni1@edublogs.org",
      "Female",
      "United States"
    ),
    Student(3, "Jesse", "Chismon", "jchismon2@hostgator.com", "Male", "China"),
    Student(
      4,
      "Delmore",
      "Scriver",
      "dscriver3@boston.com",
      "Male",
      "United States"
    ),
    Student(
      5,
      "Jocelyn",
      "Blaxlande",
      "jblaxlande4@europa.eu",
      "Female",
      "China"
    )
  )

  // Method to parse CSV string and create a Student object
  def apply(csv: String): Student = {
    val fields = csv.split(",").map(_.trim)
    Student(
      fields(0).toInt, // id
      fields(1), // firstName
      fields(2), // lastName
      fields(3), // email
      fields(4), // gender
      fields(5) // country
    )
  }
  // Problem 5.3. Function to get student names by country
  def studentNamesByCountry(country: String): List[String] = {
    allStudents
      .filter(_.country == country) // Filter students by country
      .map(student =>
        s"${student.firstName} ${student.lastName}"
      ) // Map to full names
  }

  // Problem 5.4. Function to get the total number of students by country
  def studentTotalsByCountry(country: String): Int = {
    allStudents.count(
      _.country == country
    ) // Count students from the given country
  }
}
