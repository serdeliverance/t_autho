package com.challenge.domain.validation

import com.challenge.application.utils.ValidationResultOps._
import com.challenge.domain.validation.ValidationResult.{ValidationAction, ValidationResult}

class ValidationAggregator {

  def validateAll(validations: ValidationAction*): ValidationResult =
    validations match {
      case Nil => ValidationResult.valid
      case ::(currentValidation, remainingValidations) =>
        val validationResult = currentValidation()
        if (validationResult.isNotValid()) validationResult
        else validateAll(remainingValidations: _*)
    }
}
