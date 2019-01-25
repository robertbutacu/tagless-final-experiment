package helpers

import scala.language.higherKinds

trait Logging[F[_]] {
  def error(s: String):   F[Unit]
  def message(s: String): F[Unit]
  def warning(s: String): F[Unit]
}
