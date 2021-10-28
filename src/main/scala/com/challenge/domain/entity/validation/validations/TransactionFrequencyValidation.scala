package com.challenge.domain.entity.validation.validations

import com.challenge.domain.entity.TransactionOps.TransactionExtensionMethods
import com.challenge.domain.entity.validation.ValidationResultOps.OptionValidationResultOps
import com.challenge.domain.entity.validation._
import com.challenge.domain.entity.validation.validations.validation.{AccountProvider, HIGH_FREQUENCY_SMALL_INTERVAL_MESSAGE}
import com.challenge.domain.entity.{Interval, Transaction}

import java.time.LocalDateTime

class TransactionFrequencyValidation(val intervalInMinutes: Int, val maxAllowed: Int) extends Validation {
  def validate(transaction: Transaction)(implicit accountProvider: AccountProvider): ValidationResult =
    accountProvider().map { account =>
      val interval = Interval(LocalDateTime.now().minusMinutes(intervalInMinutes), LocalDateTime.now())

      if (account.transactions.count(tx => interval.isOnInterval(tx.time)) >= maxAllowed)
        Failure(Some(account), List(HIGH_FREQUENCY_SMALL_INTERVAL_MESSAGE))
      else Success()
    }.value
}
