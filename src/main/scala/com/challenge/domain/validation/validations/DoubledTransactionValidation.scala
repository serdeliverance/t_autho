package com.challenge.domain.validation.validations

import com.challenge.domain.Transaction
import com.challenge.domain.utils.Interval
import com.challenge.domain.validation.ValidationResultOps.OptionValidationResultOps
import com.challenge.domain.validation._

import java.time.LocalDateTime

class DoubledTransactionValidation(intervalInMinutes: Int, maxAllowed: Int) extends Validation {

  def validate(accountProvider: AccountProvider, transaction: Transaction): ValidationResult =
    accountProvider
      .get()
      .map { account =>
        val interval = Interval(LocalDateTime.now().minusMinutes(intervalInMinutes), LocalDateTime.now())
        if (account.transactions.count(t => interval.isOnInterval(t.time) && areDuplicated(t, transaction)) >= maxAllowed)
          Failure(Some(account), List(DOUBLED_TRANSACTION_MESSAGE))
        else Success()
      }
      .value

  private def areDuplicated(txA: Transaction, txB: Transaction) =
    txA.merchant == txB.merchant && txA.amount == txB.amount
}
