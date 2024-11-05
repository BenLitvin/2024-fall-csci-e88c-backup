package org.cscie88c.week6

// Write a generic trait AddableTypeclass parameterized by type A
trait AddableTypeclass[A] {
  def addTwoValues(a: A, b: A): A
}

object AddableTypeclass {

  implicit val intAddableTypeclass: AddableTypeclass[Int] =
    new AddableTypeclass[Int] {
      override def addTwoValues(a: Int, b: Int): Int = a + b
    }

  implicit val boolAddableTypeclass: AddableTypeclass[Boolean] =
    new AddableTypeclass[Boolean] {
      override def addTwoValues(a: Boolean, b: Boolean): Boolean = a || b
    }

}

object AddableAggregator {
  def sumWithAddable[A](
      list: List[A]
  )(implicit addable: AddableTypeclass[A]): A = {
    list.reduce(addable.addTwoValues)
  }
}
