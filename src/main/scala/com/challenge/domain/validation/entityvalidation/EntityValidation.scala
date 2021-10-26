package com.challenge.domain.validation.entityvalidation

import com.challenge.domain.validation.ValidationResult.ValidationResult
import com.challenge.domain.{Account, Transaction}

trait EntityValidation {
  def validate(account: Account, transaction: Transaction): ValidationResult
}
