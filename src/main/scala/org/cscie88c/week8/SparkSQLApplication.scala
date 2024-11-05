package org.cscie88c.week8

import org.apache.spark.sql.SparkSession
import com.typesafe.scalalogging.{LazyLogging}
import org.cscie88c.config.{ConfigUtils}
import org.cscie88c.utils.{SparkUtils}
import org.apache.spark.sql.{Dataset, DataFrame, Row}
import pureconfig.generic.auto._
import org.apache.spark.sql.functions.{col, when}
import pureconfig.ConfigSource

// run with: sbt "runMain org.cscie88c.week8.SparkSQLApplication"
object SparkSQLApplication {

  def main(args: Array[String]): Unit = {
    implicit val conf: SparkDSConfig = readConfig()
    val spark = SparkUtils.sparkSession(conf.name, conf.masterUrl)
    val transactionDF = loadData(spark)
    val augmentedTransactionsDF = addCategoryColumn(transactionDF)
    augmentedTransactionsDF.createOrReplaceTempView("transactions")

    // 4.3 ***
    val sparkSQL = """
      SELECT category, SUM(tran_amount) AS total
      FROM transactions
      GROUP BY category
    """
    val totalsByCategoryDF = spark.sql(sparkSQL)
    printTransactionTotalsByCategory(totalsByCategoryDF)
    spark.stop()
  }

  // ***
  def readConfig(): SparkDSConfig = {
    ConfigSource.default
      .at("org.cscie88c.spark-ds-application")
      .loadOrThrow[SparkDSConfig]
  }

  // 4.1.
  def loadData(spark: SparkSession)(implicit conf: SparkDSConfig): DataFrame = {
    spark.read
      .option("header", "true")
      .option("inferSchema", "true")
      .csv(conf.transactionFile)
  }

  // 4.2.
  def addCategoryColumn(raw: DataFrame): DataFrame = {
    raw.withColumn(
      "category",
      when(col("tran_amount") > 80, "High").otherwise("Standard")
    )
  }

  // 4.4.
  def printTransactionTotalsByCategory(df: DataFrame): Unit = {
    df.collect().foreach { row =>
      val category = row.getAs[String]("category")
      val total = row.getAs[Long]("total")
      println(s"Category: $category, Total: $total")
    }
  }
}
