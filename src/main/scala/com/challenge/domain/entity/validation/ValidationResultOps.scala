package com.challenge.domain.entity.validation

object ValidationResultOps {
  implicit class OptionValidationResultOps(optValidationResult: Option[ValidationResult]) {
    def value: ValidationResult = optValidationResult match {
      case Some(validationResult) => validationResult
      case None                   => Failure(None, List("invalid-operation"))
    }
  }
}
