package services

import cats.{Applicative, MonadError, Traverse}
import cats.syntax.flatMap._
import cats.syntax.functor._
import helpers.MLogger
import models.Book
import models.types.{Author, Year}
import repositories.BookRepositoryAlgebra

import scala.language.higherKinds

trait BookServiceAlgebra[F[_]] {
  def getBooksByYearAndAuthor(year: Year, author: Author): F[List[Book]]

  def getAllBooks():                F[List[Book]]
  def postBooks(books: List[Book]): F[Unit]
  def deleteBook(book: Book):       F[Unit]
}

class BookService[F[_]](mLogger: MLogger[F], bookRepository: BookRepositoryAlgebra[F]
                           )(implicit M: MonadError[F, Throwable]) extends BookServiceAlgebra[F] {
  override def getBooksByYearAndAuthor(year: Year, author: Author): F[List[Book]] = {
    M.map(bookRepository.getBookByYear(year))(books => books.filter(b => b.author == author))
  }

  override def getAllBooks(): F[List[Book]] = bookRepository.getAllBooks()

  override def postBooks(books: List[Book]): F[Unit] = {
    M.recover {
      books.foldLeft(M.unit) { (_, b) =>
        mLogger.message(s"Creating new book $b")
        bookRepository.createBook(b)
      }
    }(???)
  }

  override def deleteBook(book: Book): F[Unit] = {
    for {
      _ <- mLogger.message(s"Deleting book $book")
      _ <- bookRepository.deleteBookByTitle(book.title)
    } yield ()
  }
}