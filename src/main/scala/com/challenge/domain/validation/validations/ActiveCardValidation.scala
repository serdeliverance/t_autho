package com.challenge.domain.validation.validations

import com.challenge.domain.Transaction
import com.challenge.domain.validation.ValidationResultOps.OptionValidationResultOps
import com.challenge.domain.validation._

class ActiveCardValidation() extends Validation {

  def validate(accountProvider: AccountProvider, transaction: Transaction): ValidationResult =
    accountProvider
      .get()
      .map { account =>
        if (account.activeCard) Success(Some(account)) else Failure(None, List(CARD_NOT_ACTIVE_MESSAGE))
      }
      .value
}
