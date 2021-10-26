package com.challenge.application.utils

import com.challenge.domain.validation.ValidationResult.ValidationResult

object ValidationResultOps {
  implicit class ValidationResultOps(validationResult: ValidationResult) {
    def isValid(): Boolean    = validationResult.isRight
    def isNotValid(): Boolean = validationResult.isLeft
  }
}
