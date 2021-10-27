package com.challenge.domain.validation

import com.challenge.domain.Transaction

trait Validation {
  def validate(transaction: Transaction)(implicit accountProvider: AccountProvider): ValidationResult
}
