package com.challenge.domain.validation.entityvalidation

import com.challenge.domain._
import com.challenge.domain.utils.Interval
import com.challenge.domain.validation.ValidationResult._
import com.challenge.domain.validation._

import java.time.LocalDateTime

class HighFrequencyValidation(val intervalInMinutes: Int, val maxAllowed: Int) extends EntityValidation {
  def validate(account: Account, transaction: Transaction): ValidationResult.ValidationResult = {
    val interval = Interval(LocalDateTime.now().minusMinutes(intervalInMinutes), LocalDateTime.now())

    if (account.transactions.count(tx => interval.isOnInterval(tx.time)) >= maxAllowed)
      Failure(Some(account), List(HIGH_FREQUENCY_SMALL_INTERVAL_MESSAGE))
    else Success()
  }
}
