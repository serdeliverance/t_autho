package com.challenge.domain.entity.validation.validations

import com.challenge.domain.entity.validation.ValidationResultOps.OptionValidationResultOps
import com.challenge.domain.entity.validation._
import com.challenge.domain.entity.validation.validations.validation.{AccountProvider, DOUBLED_TRANSACTION_MESSAGE}
import com.challenge.domain.entity.{Interval, Transaction}

import java.time.Instant
import java.time.temporal.ChronoUnit

class DoubledTransactionValidation(intervalInMinutes: Int) extends Validation {

  def validate(transaction: Transaction)(implicit accountProvider: AccountProvider): ValidationResult =
    accountProvider().map { account =>
      val interval = Interval(Instant.now().minus(intervalInMinutes, ChronoUnit.MINUTES), Instant.now())
      if (account.transactions.exists(t => interval.isOnInterval(t.time) && t == transaction))
        Failure(Some(account), List(DOUBLED_TRANSACTION_MESSAGE))
      else Success()
    }.value
}
