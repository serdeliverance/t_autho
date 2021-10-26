package com.challenge.domain.validation

import com.challenge.domain.validation.ValidationResult._

class ValidationAggregator() {

  def validateAll(validations: ValidationAction*): ValidationResult =
    validations match {
      case Nil => Success()
      case ::(currentValidation, remainingValidations) =>
        val validationResult = currentValidation()

        validationResult match {
          case Failure(_, _) => validationResult
          case Success(_)    => validateAll(remainingValidations: _*)
        }
    }
}
