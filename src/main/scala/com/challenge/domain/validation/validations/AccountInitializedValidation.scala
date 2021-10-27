package com.challenge.domain.validation.validations

import com.challenge.domain.Transaction
import com.challenge.domain.validation._

class AccountInitializedValidation() extends Validation {
  def validate(transaction: Transaction)(implicit accountProvider: AccountProvider): ValidationResult =
    if (accountProvider().nonEmpty) Success() else Failure(None, List(ACCOUNT_NOT_INITIALIZED_MESSAGE))
}
