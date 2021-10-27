package com.challenge.domain.entity.validation

import com.challenge.domain.entity.Transaction
import com.challenge.domain.entity.validation.validations.validation.AccountProvider

trait Validation {
  def validate(transaction: Transaction)(implicit accountProvider: AccountProvider): ValidationResult
}
