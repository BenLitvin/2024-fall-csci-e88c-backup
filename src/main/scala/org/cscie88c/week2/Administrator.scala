package org.cscie88c.week2

// Administrator class that inheriting from UniversityEmployee
class Administrator(name: String, email: String, val budget: Long)
    extends UniversityEmployee(name, email) {

  // Overriding the description method to include the budget
  override def description: String =
    s"Name: $name, Email: $email, Budget: $$${budget.toString}"
}
