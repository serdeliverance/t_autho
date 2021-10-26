package com.challenge.domain.validation

import com.challenge.domain.validation.ValidationResult._

trait Precondition {
  val preCondition: () => Boolean
  val errorMessage: String
  def evalPrecondition(): ValidationResult =
    if (preCondition()) Success() else Failure(None, List(errorMessage))
}
