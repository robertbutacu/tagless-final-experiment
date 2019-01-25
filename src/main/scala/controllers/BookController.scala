package controllers

import models.Book
import models.types.{Author, Year}

import scala.language.higherKinds

trait BookController[F[_]] {
  def createBook(book: Book): F[Unit]
  def deleteBook(book: Book): F[Unit]
  def getBookByYearAndAuthor(year: Year, author: Author): F[List[Book]]
  def getAllBooks():          F[List[Book]]
}
