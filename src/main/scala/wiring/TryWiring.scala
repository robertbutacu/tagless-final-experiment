package wiring

import controllers.BookController
import helpers.PrintoutMLogger
import repositories.BookRepository
import services.BookService
import scala.util.Try
import cats.instances.try_._

trait TryWiring {
  import com.softwaremill.macwire.wire

  lazy val logger     = wire[PrintoutMLogger[Try]]
  lazy val controller = wire[BookController[Try]]
  lazy val service    = wire[BookService[Try]]
  lazy val repository = wire[BookRepository[Try]]
}
