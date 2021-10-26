package com.challenge.domain.validation.entityvalidation

import com.challenge.domain.validation.ValidationResult.{Failure, Success, ValidationResult, reduce}
import com.challenge.domain.{Account, Transaction}

case class EntityValidationAggregator(validations: List[EntityValidation]) {
  def validate(account: Account, transaction: Transaction): ValidationResult = {
    val violations = validations
      .map(v => v.validate(account, transaction))
      .collect {
        case violation @ Failure(_, _) => violation
      }
    if (violations.isEmpty) Success() else reduce(violations)
  }

}
