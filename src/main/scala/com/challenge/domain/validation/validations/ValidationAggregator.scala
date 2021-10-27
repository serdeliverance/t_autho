package com.challenge.domain.validation.validations

import com.challenge.domain.Transaction
import com.challenge.domain.validation.ValidationResult._
import com.challenge.domain.validation.{AccountProvider, Validation, ValidationResult}

class ValidationAggregator(validations: List[Validation]) extends Validation {

  def validate(transaction: Transaction)(implicit accountProvider: AccountProvider): ValidationResult =
    reduce(validations.map(v => v.validate(transaction)))
}
