package com.challenge.domain.validation.validations

import com.challenge.domain.Transaction
import com.challenge.domain.TransactionOps.TransactionExtensionMethods
import com.challenge.domain.utils.Interval
import com.challenge.domain.validation.ValidationResultOps.OptionValidationResultOps
import com.challenge.domain.validation._

import java.time.LocalDateTime

class DoubledTransactionValidation(intervalInMinutes: Int, maxAllowed: Int) extends Validation {

  def validate(transaction: Transaction)(implicit accountProvider: AccountProvider): ValidationResult =
    accountProvider().map { account =>
      val interval = Interval(LocalDateTime.now().minusMinutes(intervalInMinutes), LocalDateTime.now())
      if (account.transactions.count(t => interval.isOnInterval(t.time) && t == transaction) >= maxAllowed)
        Failure(Some(account), List(DOUBLED_TRANSACTION_MESSAGE))
      else Success()
    }.value
}
