package com.challenge.application.utils

import com.challenge.domain.validation.ValidationResult

object ValidationResultOps {
  implicit class ValidationResultOps[T](validationResult: ValidationResult[T]) {
    def isValid(): Boolean = validationResult.isRight
  }
}
