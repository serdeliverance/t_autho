package com.challenge.domain.validation

import com.challenge.domain.Transaction
import com.challenge.domain.validation.ValidationResult._

class ValidationAggregator(validations: List[Validation]) extends Validation {

  def validate(accountProvider: AccountProvider, transaction: Transaction): ValidationResult =
    reduce(validations.map(v => v.validate(accountProvider, transaction)))
}
