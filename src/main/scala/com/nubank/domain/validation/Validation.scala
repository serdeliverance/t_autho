package com.nubank.domain.validation

import com.nubank.domain.{Account, Transaction}

trait Validation {
  def validate(account: Account, transaction: Transaction): ValidationResult
}
