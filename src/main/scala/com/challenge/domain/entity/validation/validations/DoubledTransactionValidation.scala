package com.challenge.domain.entity.validation.validations

import com.challenge.domain.entity.TransactionOps.TransactionExtensionMethods
import com.challenge.domain.entity.validation.ValidationResultOps.OptionValidationResultOps
import com.challenge.domain.entity.validation._
import com.challenge.domain.entity.validation.validations.validation.{AccountProvider, DOUBLED_TRANSACTION_MESSAGE}
import com.challenge.domain.entity.{Interval, Transaction}

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
