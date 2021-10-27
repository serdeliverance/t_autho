package com.challenge.domain.entity.validation.validations

import com.challenge.domain.entity.Transaction
import com.challenge.domain.entity.validation._
import com.challenge.domain.entity.validation.validations.validation.{ACCOUNT_NOT_INITIALIZED_MESSAGE, AccountProvider}

class AccountInitializedValidation() extends Validation {
  def validate(transaction: Transaction)(implicit accountProvider: AccountProvider): ValidationResult =
    if (accountProvider().nonEmpty) Success() else Failure(None, List(ACCOUNT_NOT_INITIALIZED_MESSAGE))
}
