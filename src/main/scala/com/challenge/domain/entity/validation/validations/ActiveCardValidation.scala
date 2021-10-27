package com.challenge.domain.entity.validation.validations

import com.challenge.domain.entity.Transaction
import com.challenge.domain.entity.validation.ValidationResultOps.OptionValidationResultOps
import com.challenge.domain.entity.validation._
import com.challenge.domain.entity.validation.validations.validation.{AccountProvider, CARD_NOT_ACTIVE_MESSAGE}

class ActiveCardValidation() extends Validation {

  def validate(transaction: Transaction)(implicit accountProvider: AccountProvider): ValidationResult =
    accountProvider().map { account =>
      if (account.activeCard) Success(Some(account)) else Failure(None, List(CARD_NOT_ACTIVE_MESSAGE))
    }.value
}
