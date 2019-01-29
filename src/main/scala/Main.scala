import models.Book
import wiring.Wiring

import scala.concurrent.{Await, Future}
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.duration.Duration

object Main extends App with Wiring {

  val books = for {
    list  <- Future((0 to 10).toList)
    _     <- Future.traverse(list)(_ => controller.createBook(Book.bookGenerator.sample.get))
    books <- controller.getAllBooks()
  } yield books

  val results = Await.result(books, Duration.Inf)

  results.zipWithIndex.foreach {
    b =>
      println(s"${b._2} : ${b._1}")
  }
}
