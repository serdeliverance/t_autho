package com.challenge.domain.validation

import com.challenge.domain.Transaction

trait Validation {
  def validate(accountProvider: AccountProvider, transaction: Transaction): ValidationResult
}
