package com.challenge.domain.validation

import com.challenge.domain.validation.ValidationResult.{Failure, ValidationResult}

object ValidationResultOps {
  implicit class OptionValidationResultOps(optValidationResult: Option[ValidationResult]) {
    def value: ValidationResult = optValidationResult match {
      case Some(validationResult) => validationResult
      case None                   => Failure(None, List("invalid-operation"))
    }
  }
}
