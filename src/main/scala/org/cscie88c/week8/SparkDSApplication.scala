package org.cscie88c.week8

import org.apache.spark.sql.SparkSession
import com.typesafe.scalalogging.LazyLogging
import org.cscie88c.config.ConfigUtils
import org.cscie88c.utils.SparkUtils
import org.apache.spark.sql.Dataset
import pureconfig.generic.auto._
import pureconfig.ConfigSource

// write config case class below
case class SparkDSConfig(
    name: String,
    masterUrl: String,
    transactionFile: String
)

// run with: sbt "runMain org.cscie88c.week9.SparkDSApplication"
object SparkDSApplication {

  // application main entry point
  def main(args: Array[String]): Unit = {
    implicit val conf: SparkDSConfig = readConfig()
    val spark = SparkUtils.sparkSession(conf.name, conf.masterUrl)
    val transactionDS = loadData(spark)
    val totalsByCategoryDS = transactionTotalsByCategory(spark, transactionDS)
    printTransactionTotalsByCategory(totalsByCategoryDS)
    spark.stop()
  }

  // Task 3.1
  def readConfig(): SparkDSConfig = {
    ConfigSource.default
      .at("org.cscie88c.spark-ds-application")
      .loadOrThrow[SparkDSConfig]
  }

  // Task 3.2
  def loadData(
      spark: SparkSession
  )(implicit conf: SparkDSConfig): Dataset[CustomerTransaction] = {
    import spark.implicits._

    val rawTransactions = spark.read
      .option("header", "true")
      .option("inferSchema", "true")
      .csv(conf.transactionFile)
      .as[RawTransaction]

    rawTransactions.map(CustomerTransaction.apply)
  }

  // Task 3.3
  def transactionTotalsByCategory(
      spark: SparkSession,
      transactions: Dataset[CustomerTransaction]
  ): Dataset[(String, Double)] = {
    import spark.implicits._

    transactions
      .map { transaction =>
        (transaction.transactionCategory, transaction.transactionAmount)
      }
      .groupByKey(_._1)
      .mapValues(_._2)
      .reduceGroups(_ + _)
      .map { case (category, total) => (category, total) }
  }

  // Task 3.4
  def printTransactionTotalsByCategory(ds: Dataset[(String, Double)]): Unit = {
    ds.collect().foreach { case (category, total) =>
      println(s"Category: $category, Total: $total")
    }
  }
}
