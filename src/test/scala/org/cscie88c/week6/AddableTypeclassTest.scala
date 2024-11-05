package org.cscie88c.week6

import org.cscie88c.testutils.{ StandardTest }

class AddableTypeclassTest extends StandardTest {
  
  "AddableAggregator" should {
    "sum a list of integers" in { 
      // Test for summing a list of integers
          val intList = List(1, 2, 3, 4)
      AddableAggregator.sumWithAddable(intList) shouldBe 10
      
      // Test case with a single integer
      val singleInt = List(5)
      AddableAggregator.sumWithAddable(singleInt) shouldBe 5

      // Test with an empty list (throws an exception)
      val emptyIntList = List.empty[Int]
      assertThrows[UnsupportedOperationException] {
        AddableAggregator.sumWithAddable(emptyIntList)
      }
    }
    "sum a list of booleans" in {
      // Test for summing a list of booleans
      val boolList = List(true, false, true)
      AddableAggregator.sumWithAddable(boolList) shouldBe true
      
      // Test with all true values
      val allTrueList = List(true, true, true)
      AddableAggregator.sumWithAddable(allTrueList) shouldBe true

      // Test with all false values
      val allFalseList = List(false, false, false)
      AddableAggregator.sumWithAddable(allFalseList) shouldBe false

      // Test with a single boolean value
      val singleTrue = List(true)
      AddableAggregator.sumWithAddable(singleTrue) shouldBe true

      val singleFalse = List(false)
      AddableAggregator.sumWithAddable(singleFalse) shouldBe false

      // Test with an empty list (throws an exception)
      val emptyBoolList = List.empty[Boolean]
      assertThrows[UnsupportedOperationException] {
        AddableAggregator.sumWithAddable(emptyBoolList)
      }
    }
    "sum a list of employees" in {

      // Implement Employee addable typeclass first

      val employees = List(
        Employee("Alice", 30, 50000),
        Employee("Bob", 25, 60000),
        Employee("Charlie", 35, 55000)
      )

      // Ensure that the sum of salaries works correctly
      AddableAggregator.sumWithAddable(employees.map(_.salary)) shouldBe 165000

      // Case with a single employee
      val singleEmployee = List(Employee("Alice", 30, 50000))
      AddableAggregator.sumWithAddable(singleEmployee.map(_.salary)) shouldBe 50000

      // Test with an empty list (throws an exception)
      val emptyEmployeeList = List.empty[Employee]
      assertThrows[UnsupportedOperationException] {
        AddableAggregator.sumWithAddable(emptyEmployeeList.map(_.salary))
      }
    }
  }
}
 