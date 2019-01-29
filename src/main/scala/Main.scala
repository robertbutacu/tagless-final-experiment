import models.Book
import wiring.{FutureWiring, TryWiring}

import scala.concurrent.{Await, Future}
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.duration.Duration
import scala.util.Try

object Main extends App with TryWiring {
/*
  val books = for {
    list  <- Future((0 to 10).toList)
    _     <- Future.traverse(list)(_ => controller.createBook(Book.bookGenerator.sample.get))
    books <- controller.getAllBooks()
  } yield books

  val results = Await.result(books, Duration.Inf)

  results.zipWithIndex.foreach {
    b =>
      println(s"${b._2} : ${b._1}")
  }*/

  val books = for {
    list  <- Try((0 to 10).toList)
    _     <- Try(list.map(_ => controller.createBook(Book.bookGenerator.sample.get)))
    books <- controller.getAllBooks()
  } yield books

  books.get.zipWithIndex.foreach {
    b =>
      println(s"${b._2} : ${b._1}")
  }
}
