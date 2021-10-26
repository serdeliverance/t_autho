package com.challenge.domain.validation.entityvalidation

import com.challenge.domain.validation.ACCOUNT_NOT_INITIALIZED_MESSAGE
import com.challenge.domain.validation.ValidationResult._
import com.challenge.domain.{Account, Transaction}

class AccountNotInitializedValidation() extends EntityValidation {

  def validate(account: Account, transaction: Transaction): ValidationResult =
    if (account.activeCard) Success(Some(account)) else Failure(None, List(ACCOUNT_NOT_INITIALIZED_MESSAGE))
}
