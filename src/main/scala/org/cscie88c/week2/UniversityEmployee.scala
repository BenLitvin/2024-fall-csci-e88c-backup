package org.cscie88c.week2

// Define the class UniversityEmployee
class UniversityEmployee(val name: String, val email: String) {

  // Method to return a description string
  def description: String = s"Name: $name, Email: $email"
}
