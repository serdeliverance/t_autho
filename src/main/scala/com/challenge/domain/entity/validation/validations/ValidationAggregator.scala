package com.challenge.domain.entity.validation.validations

import com.challenge.domain.entity.Transaction
import com.challenge.domain.entity.validation.ValidationResult._
import com.challenge.domain.entity.validation.validations.validation.AccountProvider
import com.challenge.domain.entity.validation.{AccountProvider, Validation, ValidationResult}

class ValidationAggregator(validations: List[Validation]) extends Validation {

  def validate(transaction: Transaction)(implicit accountProvider: AccountProvider): ValidationResult =
    reduce(validations.map(v => v.validate(transaction)))
}
