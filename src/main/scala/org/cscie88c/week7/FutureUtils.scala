package org.cscie88c.week7

import scala.concurrent.{Future}
import scala.util.{Try, Success, Failure}
import scala.concurrent.ExecutionContext.Implicits.global
import scala.util.Random
import scala.collection.parallel.CollectionConverters._

object FutureUtils {

  val rnd = new Random()

  def creditScoreAPI(applicantId: Int): Future[Int] = Future {
    val creditScore = 300 + rnd.nextInt(501)
    creditScore
  }

  def printCreditScore(applicantId: Int): Unit = {
    val creditScoreFuture = creditScoreAPI(applicantId)

    creditScoreFuture.onComplete {
      case Success(score) =>
        println(s"The credit score for $applicantId is: $score")
      case Failure(_) =>
        println(s"Cannot get credit score for $applicantId at this time")
    }
  }

  def passedCreditCheck(applicantId: Int): Future[Boolean] = {
    creditScoreAPI(applicantId).map { score =>
      score > 600
    }
  }

  def futureFactorial(n: Int): Future[Int] = Future {
    require(n >= 0, "Factorial is not defined for negative numbers")

    def factorial(n: Int): Int = {
      if (n == 0 || n == 1) 1
      else n * factorial(n - 1)
    }

    factorial(n)
  }

  def futurePermutations(n: Int, r: Int): Future[Int] = {
    for {
      nFactorial <- futureFactorial(n) // calculating n!
      nMinusRFactorial <- futureFactorial(n - r) // calculating (n - r)!
    } yield nFactorial / nMinusRFactorial // calculating n! / (n - r)!
  }

  def asyncAverageCreditScore(idList: List[Int]): Future[Double] = {
    // First, we get credit scores for all applicants asynchronously
    val futures: List[Future[Int]] = idList.map(creditScoreAPI)

    // Then we use Future.sequence to convert List[Future[Int]] into Future[List[Int]]
    val futureScores: Future[List[Int]] = Future.sequence(futures)

    // And then we calculate the average score when all scores are available
    futureScores.map { scores =>
      scores.sum.toDouble / scores.length
    }
  }

  def slowMultiplication(x: Long, y: Long): Long = {
    Thread.sleep(1000)
    x * y
  }

  def sequentialFactorial(n: Long): Long = {
    (1L to n).reduce(slowMultiplication)
  }

  def concurrentFactorial(n: Long): Long = {
    (1L to n).par.reduce(slowMultiplication)
  }
}
