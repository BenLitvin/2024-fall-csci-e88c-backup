package org.cscie88c.week6

import org.cscie88c.testutils.{ StandardTest }

class AddableTraitTest extends StandardTest {

  "plus" should {

    "add two MyInt values correctly" in {
      // add your unit tests for MyInt below
      val int1 = MyInt(5)
      val int2 = MyInt(3)
      int1.plus(int2) shouldBe MyInt(8)
    }

    "add two MyBool values correctly" in {
      // add your unit tests for MyBool below
      val bool1 = MyBool(true)
      val bool2 = MyBool(false)
      bool1.plus(bool2) shouldBe MyBool(true)

      val bool3 = MyBool(true)
      bool1.plus(bool3) shouldBe MyBool(true)

      val bool4 = MyBool(false)
      bool2.plus(bool4) shouldBe MyBool(false)
    }
   } 
}
 