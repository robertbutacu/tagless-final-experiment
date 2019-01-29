package controllers

import models.Book
import models.types.{Author, Year}
import services.BookServiceAlgebra

import scala.language.higherKinds

trait BookControllerAlgebra[F[_]] {
  def createBook(book: Book): F[Unit]
  def deleteBook(book: Book): F[Unit]
  def getBookByYearAndAuthor(year: Year, author: Author): F[List[Book]]
  def getAllBooks():          F[List[Book]]
}

class BookController[F[_]](bookService: BookServiceAlgebra[F]) extends BookControllerAlgebra[F] {
  override def createBook(book: Book): F[Unit] = {
    bookService.postBooks(List(book))
  }

  override def deleteBook(book: Book): F[Unit] = {
    bookService.deleteBook(book)
  }

  override def getBookByYearAndAuthor(year: Year, author: Author): F[List[Book]] = {
    bookService.getBooksByYearAndAuthor(year, author)
  }

  override def getAllBooks(): F[List[Book]] = {
    bookService.getAllBooks()
  }
}