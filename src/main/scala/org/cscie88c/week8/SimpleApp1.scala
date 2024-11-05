package org.cscie88c.week8

import com.typesafe.scalalogging.LazyLogging
import scala.util.{Try, Success, Failure}
import scala.io.Source

import pureconfig.ConfigSource
import pureconfig.generic.auto._

// Problem 1b
case class SimpleApp1Config(fileName: String, month: String)

// run using: sbt "runMain org.cscie88c.week8.SimpleApp1 <args>"
object SimpleApp1 extends LazyLogging {

  def lineStreamFromFile(fileName: String): Option[LazyList[String]] =
    Try {
      LazyList.from(Source.fromResource(fileName).getLines())
    }.toOption

  def monthLines(month: String)(lines: LazyList[String]): LazyList[String] =
    lines.filter(_.toLowerCase.contains(month.toLowerCase))

  def main(args: Array[String]): Unit = {

    val config = ConfigSource.default
      .at("org.cscie88c.simple-app-1")
      .load[SimpleApp1Config]

    config match {
      case Right(conf) =>
        lineStreamFromFile(conf.fileName) match {
          case Some(lines) =>
            val filteredLines = monthLines(conf.month)(lines)
            println(s"Transactions in ${conf.month}: ${filteredLines.size}")
          case None =>
            println(s"No Transactions found for ${conf.month}")
        }
      case Left(error) =>
        logger.error("Error loading configuration: ", error)
    }
  }

}
