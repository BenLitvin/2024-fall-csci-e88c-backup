package org.cscie88c.week2

final case class Subject(
    id: Int,
    name: String,
    isSTEM: Boolean
)

object Subject {

  // List of all subjects
  val allSubjects: List[Subject] = List(
    Subject(1, "Physics", true),
    Subject(2, "Chemistry", true),
    Subject(3, "Math", true),
    Subject(4, "English", false)
  )

  // Method to parse CSV string and create a Subject object
  def apply(csv: String): Subject = {
    val fields = csv.split(",").map(_.trim)
    Subject(
      fields(0).toInt, // id
      fields(1), // name
      fields(2).toBoolean // isSTEM (convert to Boolean)
    )
  }

  // Problem 5.5. Function to get all STEM subjects
  def stemSubjects: List[Subject] = {
    allSubjects.filter(_.isSTEM) // Filter subjects where isSTEM is true
  }
}
