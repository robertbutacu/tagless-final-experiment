package services

import models.Book
import models.types.{Author, Year}

import scala.language.higherKinds

trait BookService[F[_]] {
  def getBooksByYearAndAuthor(year: Year, author: Author): F[List[Book]]

  def getAllBooks():                F[List[Book]]
  def postBooks(books: List[Book]): F[Unit]
  def deleteBook(book: Book):       F[Unit]
}
