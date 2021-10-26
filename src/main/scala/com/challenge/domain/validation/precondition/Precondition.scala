package com.challenge.domain.validation.precondition

import com.challenge.domain.validation.ValidationResult.{Failure, Success, ValidationResult}

trait Precondition {
  val preCondition: () => Boolean
  val errorMessage: String
  def evalPrecondition(): ValidationResult =
    if (preCondition()) Success() else Failure(None, List(errorMessage))
}
