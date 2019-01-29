package wiring

import controllers.BookController
import helpers.PrintoutMLogger
import repositories.BookRepository
import services.BookService
import cats.instances.future._
import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global

trait FutureWiring {
  import com.softwaremill.macwire.wire

  lazy val logger     = wire[PrintoutMLogger[Future]]
  lazy val controller = wire[BookController[Future]]
  lazy val service    = wire[BookService[Future]]
  lazy val repository = wire[BookRepository[Future]]
}
