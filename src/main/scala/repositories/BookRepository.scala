package repositories

import cats.Monad
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

class BookRepositoryImpl[F[_]](implicit M: Monad[F]) extends BookRepository[F] {
  private var books: List[Book] = (0 to 150).map(_ => Book.bookGenerator.sample.get).toList

  override def getAllBooks(): F[List[Book]] = M.pure {
    books
  }

  override def getBookByAuthor(author: Author): F[List[Book]] = M.pure {
    books.filter(b => b.author == author)
  }

  override def getBookByYear(year: Year): F[List[Book]] = M.pure {
    books.filter(b => b.year == year)
  }

  override def createBook(book: Book): F[Unit] = M.pure {
    books = books :+ book
  }

  override def deleteBookByTitle(title: Title): F[Unit] = M.pure {
    books = books.filterNot(b => b.title == title)
  }
}