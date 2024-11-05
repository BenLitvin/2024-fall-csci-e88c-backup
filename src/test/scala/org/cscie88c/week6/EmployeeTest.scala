package org.cscie88c.week6

import org.cscie88c.testutils.{StandardTest}
import AddableAggregator.sumWithAddable // for the bonus task
class EmployeeTest extends StandardTest {

  // Sample data for testing
  val employees = List(
    Employee("Charlie", 25, 50000),
    Employee("Alice", 30, 60000),
    Employee("Bob", 28, 55000)
  )

  val sameNameEmployees = List(
    Employee("Charlie", 25, 50000),
    Employee("Charlie", 30, 60000),
    Employee("Charlie", 28, 55000)
  )

  val sameSalaryEmployees = List(
    Employee("Charlie", 25, 50000),
    Employee("Alice", 30, 50000),
    Employee("Bob", 28, 50000)
  )

  "Employee" should {

    "have a default sort order" in { // by name
      val sortedByName = Employee.defaultSortEmployees(employees)
      sortedByName.map(_.name) shouldBe List("Alice", "Bob", "Charlie")
    }

    // Test for sorting an empty list
    "return an empty list when given an empty list" in {
      val emptyList = List.empty[Employee]
      Employee.defaultSortEmployees(emptyList) shouldBe emptyList
      Employee.sortEmployeesBySalary(emptyList) shouldBe emptyList
    }

    // Test for sorting a list with a single employee
    "return the same list when sorting a single employee" in {
      val singleEmployeeList = List(Employee("Charlie", 25, 50000))
      Employee.defaultSortEmployees(
        singleEmployeeList
      ) shouldBe singleEmployeeList
      Employee.sortEmployeesBySalary(
        singleEmployeeList
      ) shouldBe singleEmployeeList
    }

    // Test for sorting multiple employees with the same name
    "sort employees with the same name by salary when names are equal" in {
      val sortedByName = Employee.defaultSortEmployees(sameNameEmployees)
      sortedByName.map(_.salary) shouldBe List(
        50000,
        55000,
        60000
      ) // Sort by salary when names are equal
    } // since the name is the same, it defaults to insertion order.

    "sort employees by salary" in { // by salary
      val sortedBySalary = Employee.sortEmployeesBySalary(employees)
      sortedBySalary.map(_.salary) shouldBe List(60000, 55000, 50000)
    }

    // Test for sorting multiple employees with the same salary
    "sort employees with the same salary by name when salaries are equal" in {
      val sortedBySalary = Employee.sortEmployeesBySalary(sameSalaryEmployees)
      sortedBySalary.map(_.name) shouldBe List(
        "Alice",
        "Bob",
        "Charlie"
      ) // Sort alphabetically by name when salaries are equal
    }

    // Test for sumWithAddable method - bonus task
    "add two employees using sumWithAddable" in {
      val result = sumWithAddable(
        List(Employee("Ken", 25, 80000), Employee("Burns", 35, 90000))
      )
      result shouldBe Employee("Ken,Burns", 60, 170000)
    }
  }
}
