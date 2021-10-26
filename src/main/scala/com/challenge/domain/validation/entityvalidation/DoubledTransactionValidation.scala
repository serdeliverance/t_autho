package com.challenge.domain.validation.entityvalidation

import com.challenge.domain.utils.Interval
import com.challenge.domain.validation.DOUBLED_TRANSACTION_MESSAGE
import com.challenge.domain.validation.ValidationResult._
import com.challenge.domain.{Account, Transaction}

import java.time.LocalDateTime

class DoubledTransactionValidation(intervalInMinutes: Int, maxAllowed: Int) extends EntityValidation {
  def validate(account: Account, transaction: Transaction): ValidationResult = {
    val interval = Interval(LocalDateTime.now().minusMinutes(intervalInMinutes), LocalDateTime.now())

    if (account.transactions.count(t => interval.isOnInterval(t.time) && areDuplicated(t, transaction)) >= maxAllowed)
      Failure(Some(account), List(DOUBLED_TRANSACTION_MESSAGE))
    else Success()
  }

  private def areDuplicated(txA: Transaction, txB: Transaction) =
    txA.merchant == txB.merchant && txA.amount == txB.amount
}
