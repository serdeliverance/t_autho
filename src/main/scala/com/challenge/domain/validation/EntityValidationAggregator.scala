package com.challenge.domain.validation

import com.challenge.domain.validation.ValidationResult.ValidationResult
import com.challenge.domain.{Account, Transaction}

case class EntityValidationAggregator(validations: List[EntityValidation]) {
  def validate(account: Account, transaction: Transaction): ValidationResult = {
    val violations = validations
      .map(v => v.validate(account, transaction))
      .collect {
        case Left(message) => message
      }
      .flatten
    if (violations.isEmpty) Right(account) else Left(violations)
  }

}
