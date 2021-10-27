package com.challenge.domain.entity.validation

import cats.Semigroup
import cats.implicits._
import com.challenge.domain.entity.Account

sealed trait ValidationResult
case class Failure(maybeAccount: Option[Account], violations: List[String]) extends ValidationResult
case class Success(maybeAccount: Option[Account] = None)                    extends ValidationResult

object ValidationResult {

  def reduce(validationResults: List[ValidationResult]): ValidationResult = validationResults.fold(Success())(_ |+| _)

  implicit val semigroupInstance: Semigroup[ValidationResult] = new Semigroup[ValidationResult] {
    override def combine(vra: ValidationResult, vrb: ValidationResult): ValidationResult =
      (vra, vrb) match {
        case (Success(_), Success(_))                           => Success()
        case (Success(_), Failure(_, _))                        => vrb
        case (Failure(_, _), Success(_))                        => vra
        case (Failure(_, violationsA), Failure(_, violationsB)) => Failure(None, violationsA ++ violationsB)
      }
  }
}
