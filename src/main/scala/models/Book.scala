package models

import models.types.{Author, Description, Title, Year}
import models.types.chooseYear
import org.scalacheck.{Arbitrary, Gen}
import eu.timepit.refined.auto._

case class Book(title: Title, author: Author, year: Year, description: Description)

object Book {
  def bookGenerator: Gen[Book] = {
    for {
      title       <- Gen.oneOf[Title]("History of Mankind", "An introduction on FP")
      author      <- Gen.oneOf[Author]("A B", "C D", "E F")
      year        <- Gen.choose[Year](1000, 2000)
      description <- Gen.oneOf[Description]("abdafadsasdfasdfa fasdfasdfadc", "fdeasdfas dadsfasdffasdfasdfasdfd")
    } yield Book(title, author, year, description)
  }
}