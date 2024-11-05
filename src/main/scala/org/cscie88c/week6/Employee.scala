package org.cscie88c.week6

final case class Employee(name: String, age: Int, salary: Int)

object Employee {

  // Implicit Ordering by name for default sort
  implicit val employeeOrdering: Ordering[Employee] = Ordering.by(e => (e.name, e.salary))


  // Default sorting by name (ascending)
  def defaultSortEmployees(employees: List[Employee]): List[Employee] = {
    employees.sorted
  }

  // Sorting by salary (descending)
  def sortEmployeesBySalary(employees: List[Employee]): List[Employee] = {
  val salaryOrdering: Ordering[Employee] = Ordering.by(e => (-e.salary, e.name))
  employees.sorted(salaryOrdering)
}

  // Implicit instance of AddableTypeclass for Employee
  implicit val employeeAddableTypeclass: AddableTypeclass[Employee] =
    new AddableTypeclass[Employee] {
      def addTwoValues(a: Employee, b: Employee): Employee = {
        Employee(
          name = s"${a.name},${b.name}", // concatenate names
          age = a.age + b.age, // sum ages
          salary = a.salary + b.salary // sum salaries
        )
      }
    }
}
