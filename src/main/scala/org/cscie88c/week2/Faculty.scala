package org.cscie88c.week2

// Faculty class that inheriting from UniversityEmployee
class Faculty(name: String, email: String, val courseId: String)
    extends UniversityEmployee(name, email) {

  // Overriding the description method to include courseId
  override def description: String =
    s"Name: $name, Email: $email, Course: $courseId"
}
