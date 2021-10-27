package com.challenge.domain.validation.validations

import com.challenge.domain.Transaction
import com.challenge.domain.validation._

class AccountInitializedPrecondition() extends Validation {
  def validate(accountProvider: AccountProvider, transaction: Transaction): ValidationResult =
    if (accountProvider.get().nonEmpty) Success() else Failure(None, List(ACCOUNT_NOT_INITIALIZED_MESSAGE))
}
