package com.challenge.domain.validation

import cats.data.NonEmptyList
import com.challenge.domain.Account

object ValidationResult {

  sealed trait ValidationResult
  case class Failure(maybeAccount: Option[Account], violations: NonEmptyList[String]) extends ValidationResult
  case class Success(maybeAccount: Option[Account] = None)

  type ValidationAction = () => ValidationResult
}
