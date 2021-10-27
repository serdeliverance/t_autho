package com.challenge.domain.validation.validations

import com.challenge.domain.Transaction
import com.challenge.domain.validation._

case class AccountNotInitializedValidation() extends Validation {

  def validate(accountProvider: AccountProvider, transaction: Transaction): ValidationResult =
    if (accountProvider.get().isEmpty) Success() else Failure(None, List(ACCOUNT_ALREADY_INITIALIZED_MESSAGE))
}
