package models

import models.types.{Author, Description, Title, Year}
import org.scalacheck.Gen
import eu.timepit.refined.auto._

case class Book(title: Title, author: Author, year: Year, description: Description)

object Book {
  def bookGenerator: Gen[Book] = {
    for {
      title       <- Gen.oneOf("History of Mankind", "An introduction on FP")
      author      <- Gen.oneOf("A B", "C D", "E F")
      year        <- Gen.choose(1000, 2000)
      description <- Gen.oneOf("abdafadsasdfasdfa fasdfasdfadc", "fdeasdfas dadsfasdffasdfasdfasdfd")
    } yield Book(title, author, year, description)
  }
}