package com.challenge.domain.validation

import com.challenge.domain.validation.ValidationResult.ValidationResult

trait Precondition {
  val preCondition: () => Boolean
  val errorMessage: String
  def evalPrecondition(): ValidationResult =
    if (preCondition()) ValidationResult.valid else ValidationResult.invalid(errorMessage)
}
