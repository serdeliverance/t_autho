package com.challenge.domain.validation.validations

import com.challenge.domain.Transaction
import com.challenge.domain.validation._

case class AccountNotInitializedValidation() extends Validation {

  def validate(transaction: Transaction)(implicit accountProvider: AccountProvider): ValidationResult =
    if (accountProvider().isEmpty) Success() else Failure(None, List(ACCOUNT_ALREADY_INITIALIZED_MESSAGE))
}
