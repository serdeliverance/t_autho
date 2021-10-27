package com.challenge.domain.validation.validations

import com.challenge.domain.TransactionOps.TransactionExtensionMethods
import com.challenge.domain._
import com.challenge.domain.utils.Interval
import com.challenge.domain.validation.ValidationResultOps.OptionValidationResultOps
import com.challenge.domain.validation._

import java.time.LocalDateTime

class HighFrequencyValidation(val intervalInMinutes: Int, val maxAllowed: Int) extends Validation {
  def validate(accountProvider: AccountProvider, transaction: Transaction): ValidationResult =
    accountProvider
      .get()
      .map { account =>
        val interval = Interval(LocalDateTime.now().minusMinutes(intervalInMinutes), LocalDateTime.now())

        if (account.transactions.count(tx => interval.isOnInterval(tx.time)) >= maxAllowed)
          Failure(Some(account), List(HIGH_FREQUENCY_SMALL_INTERVAL_MESSAGE))
        else Success()
      }
      .value
}
