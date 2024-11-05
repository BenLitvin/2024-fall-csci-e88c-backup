package org.cscie88c.week4

object ListUtils {
  // complete the function below
  def initDoubleList(initValue: Double)(size: Int): List[Double] = {
    List.fill(size)(initValue)
  }

  // complete the functions below using currying
  def ones: Int => List[Double] = initDoubleList(1.0) _
  def zeroes: Int => List[Double] = initDoubleList(0.0) _

  // complete the functions below
  def charCounts(sentence: String): Map[Char, Int] = {
    sentence.filter(_ != ' ').groupBy(identity).view.mapValues(_.length).toMap
  }

  def topN(n: Int)(frequencies: Map[Char, Int]): List[(Char, Int)] = {
    frequencies.toList.sortBy(-_._2).take(n)
  }

}
