package repositories

import cats.Monad
import models.Book
import models.types.{Author, Title, Year}

import scala.language.higherKinds

trait BookRepositoryAlgebra[F[_]] {
  def getAllBooks():                   F[List[Book]]
  def getBookByAuthor(author: Author): F[List[Book]]
  def getBookByYear(year: Year):       F[List[Book]]

  def createBook(book: Book):          F[Unit]

  def deleteBookByTitle(title: Title): F[Unit]
}

class BookRepository[F[_]]()(implicit M: Monad[F]) extends BookRepositoryAlgebra[F] {
  private var repository: List[Book] = List.empty[Book]

  override def getAllBooks(): F[List[Book]] = M.pure {
    repository
  }

  override def getBookByAuthor(author: Author): F[List[Book]] = M.pure {
    repository.filter(b => b.author == author)
  }

  override def getBookByYear(year: Year): F[List[Book]] = M.pure {
    repository.filter(b => b.year == year)
  }

  override def createBook(book: Book): F[Unit] = M.pure {
    repository = repository :+ book
  }

  override def deleteBookByTitle(title: Title): F[Unit] = M.pure {
    repository = repository.filterNot(b => b.title == title)
  }
}