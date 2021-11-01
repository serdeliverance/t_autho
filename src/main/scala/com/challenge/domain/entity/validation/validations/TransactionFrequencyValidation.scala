package com.challenge.domain.entity.validation.validations

import com.challenge.domain.entity.validation.ValidationResultOps.OptionValidationResultOps
import com.challenge.domain.entity.validation._
import com.challenge.domain.entity.validation.validations.validation.{AccountProvider, HIGH_FREQUENCY_SMALL_INTERVAL_MESSAGE}
import com.challenge.domain.entity.{Interval, Transaction}

import java.time.Instant
import java.time.temporal.ChronoUnit

class TransactionFrequencyValidation(val intervalInMinutes: Int, val maxAllowed: Int) extends Validation {
  def validate(transaction: Transaction)(implicit accountProvider: AccountProvider): ValidationResult =
    accountProvider().map { account =>
      val interval = Interval(Instant.now().minus(intervalInMinutes, ChronoUnit.MINUTES), Instant.now())

      if (account.transactions.count(tx => interval.isOnInterval(tx.time)) >= maxAllowed)
        Failure(Some(account), List(HIGH_FREQUENCY_SMALL_INTERVAL_MESSAGE))
      else Success()
    }.value
}
