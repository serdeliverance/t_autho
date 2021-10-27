package com.challenge.domain.entity.validation.validations

import com.challenge.domain.entity.Transaction
import com.challenge.domain.entity.validation._
import com.challenge.domain.entity.validation.validations.validation.{ACCOUNT_ALREADY_INITIALIZED_MESSAGE, AccountProvider}

case class AccountNotInitializedValidation() extends Validation {

  def validate(transaction: Transaction)(implicit accountProvider: AccountProvider): ValidationResult =
    if (accountProvider().isEmpty) Success() else Failure(None, List(ACCOUNT_ALREADY_INITIALIZED_MESSAGE))
}
