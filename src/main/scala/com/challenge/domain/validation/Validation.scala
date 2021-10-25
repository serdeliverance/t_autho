package com.challenge.domain.validation

import com.challenge.domain.{Account, Transaction}

trait Validation {
  def validate(account: Account, transaction: Transaction): ValidationResult[Account]
}
