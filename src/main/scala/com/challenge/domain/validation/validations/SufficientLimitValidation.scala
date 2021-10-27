package com.challenge.domain.validation.validations

import com.challenge.domain.Transaction
import com.challenge.domain.TransactionOps.TransactionExtensionMethods
import com.challenge.domain.validation.ValidationResultOps.OptionValidationResultOps
import com.challenge.domain.validation._

class SufficientLimitValidation extends Validation {
  def validate(transaction: Transaction)(implicit accountProvider: AccountProvider): ValidationResult =
    accountProvider().map { account =>
      if (account.balance() - transaction.amount >= 0) Success()
      else Failure(Some(account), List(INSUFFICIENT_LIMIT_MESSAGE))
    }.value
}
