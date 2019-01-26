package models

import eu.timepit.refined._
import eu.timepit.refined.api.Refined
import eu.timepit.refined.auto._
import eu.timepit.refined.boolean.{And, Or}
import eu.timepit.refined.char.{Letter, LetterOrDigit, Whitespace}
import eu.timepit.refined.collection.{Forall, MaxSize, MinSize}
import eu.timepit.refined.numeric.GreaterEqual
import org.scalacheck.Gen

package object types {
  type Author      = Refined[String, ValidAuthor]
  type Year        = Refined[Int, GreaterEqual[W.`1000`.T]]
  type Title       = Refined[String, ValidTitle]
  type Description = Refined[String, ValidDescriptionSize]

  implicit val chooseYear: Gen.Choose[Year] = (min: Year, max: Year) => {
    implicitly[Gen.Choose[Int]].choose(min, max).map(i => refineV[GreaterEqual[W.`1000`.T]](i).toOption.get)
  }

  private type ValidAuthor          = Forall[Or[Letter, Whitespace]]
  private type ValidTitle           = Forall[Or[LetterOrDigit, Whitespace]]
  private type ValidDescriptionSize = And[MinSize[W.`10`.T], MaxSize[W.`200`.T]]
}
