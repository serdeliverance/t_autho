package com.challenge.domain.entity.validation.validations

import com.challenge.domain.entity.Transaction
import com.challenge.domain.entity.validation.ValidationResultOps.OptionValidationResultOps
import com.challenge.domain.entity.validation._
import com.challenge.domain.entity.validation.validations.validation.{AccountProvider, INSUFFICIENT_LIMIT_MESSAGE}

class SufficientLimitValidation extends Validation {
  def validate(transaction: Transaction)(implicit accountProvider: AccountProvider): ValidationResult =
    accountProvider().map { account =>
      if (account.balance() - transaction.amount >= 0) Success()
      else Failure(Some(account), List(INSUFFICIENT_LIMIT_MESSAGE))
    }.value
}
