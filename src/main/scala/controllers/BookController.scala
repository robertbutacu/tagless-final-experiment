package controllers

import models.Book
import models.types.{Author, Year}
import services.BookService

import scala.language.higherKinds

trait BookController[F[_]] {
  def createBook(book: Book): F[Unit]
  def deleteBook(book: Book): F[Unit]
  def getBookByYearAndAuthor(year: Year, author: Author): F[List[Book]]
  def getAllBooks():          F[List[Book]]
}

class BookControllerImpl[F[_]](
                              bookService: BookService[F]
                              ) extends BookController[F] {
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