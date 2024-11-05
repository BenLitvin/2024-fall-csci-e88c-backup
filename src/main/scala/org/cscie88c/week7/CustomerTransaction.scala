package org.cscie88c.week7

import scala.io.Source
import scala.util.{Try, Success, Failure}

final case class CustomerTransaction(
    customerId: String,
    transactionDate: String,
    transactionAmount: Double
)

object CustomerTransaction {
  // Task 2.1
  // Here companion object apply method to parse a CSV string
  def apply(csvString: String): Option[CustomerTransaction] = {
    // Split the CSV string by commas
    val fields = csvString.split(",")

    // Then we check if the CSV has exactly 3 fields (customerId, transactionDate, transactionAmount)
    if (fields.length == 3) {
      val customerId = fields(0)
      val transactionDate = fields(1)

      // Parsing the transactionAmount as a Double
      Try(fields(2).toDouble) match {
        case Success(amount) =>
          Some(CustomerTransaction(customerId, transactionDate, amount))
        case Failure(_) => None
      }
    } else {
      None
    }
  }
  // Task 2.3
  // Function to read from a CSV file and return a list of valid CustomerTransactions
  def readFromCSVFile(fileName: String): List[CustomerTransaction] = {
    // We use Source.fromResource to read the CSV file
    val source = Source.fromResource(fileName)

    try {
      // Get lines from the file, map to Option[CustomerTransaction], and filter out None
      source
        .getLines()
        .flatMap(line =>
          CustomerTransaction(line)
        ) // with FlatMap we keep only valid transactions
        .toList
    } finally {
      source.close() // Ensure that file is closed after reading
    }
  }
}
