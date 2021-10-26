package com.challenge.domain.validation.entityvalidation

import com.challenge.domain.validation.ValidationResult._
import com.challenge.domain.validation.{INSUFFICIENT_LIMIT_MESSAGE, ValidationResult}
import com.challenge.domain.{Account, Transaction}

class SufficientLimitValidation extends EntityValidation {
  def validate(account: Account, transaction: Transaction): ValidationResult.ValidationResult =
    if (account.balance() - transaction.amount >= 0) Success()
    else Failure(Some(account), List(INSUFFICIENT_LIMIT_MESSAGE))
}
