package com.challenge.domain.validation

import com.challenge.domain.{Account, Transaction}

case class ValidationAggregator(validations: List[Validation]) {
  def validate(account: Account, transaction: Transaction): ValidationResult[Account] = {
    val violations = validations
      .map(v => v.validate(account, transaction))
      .collect {
        case Left(message) => message
      }
      .flatten
    if (violations.isEmpty) Right(account) else Left(violations)
  }

}
