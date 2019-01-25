package repositories

import models.Book
import models.types.{Author, Title, Year}

import scala.language.higherKinds

trait BookRepository[F[_]] {
  def getAllBooks():                   F[List[Book]]
  def getBookByAuthor(author: Author): F[List[Book]]
  def getBookByYear(year: Year):       F[List[Book]]

  def createBook(book: Book):          F[Unit]

  def deleteBookByTitle(title: Title): F[Unit]
}
