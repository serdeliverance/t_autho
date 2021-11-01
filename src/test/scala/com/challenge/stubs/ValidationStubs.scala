package com.challenge.stubs

import com.challenge.domain.entity.validation.Validation
import com.challenge.domain.entity.validation.validations.{AccountInitializedValidation, ActiveCardValidation, DoubledTransactionValidation, SufficientLimitValidation, TransactionFrequencyValidation, ValidationAggregator}

trait ValidationStubs {

  def createValidationAggregator(
    highFrequencyInterval: Int,
    highFrequencyMaxAllowed: Int,
    doubledTransactionInterval: Int
  ): Validation = {
    val accountInitializedValidation = new AccountInitializedValidation()
    val cardActiveValidation         = new ActiveCardValidation()
    val sufficientLimitValidation    = new SufficientLimitValidation()
    val transactionFrequencyValidation =
      new TransactionFrequencyValidation(highFrequencyInterval, highFrequencyMaxAllowed)
    val doubledTransactionValidation = new DoubledTransactionValidation(doubledTransactionInterval)

    ValidationAggregator(
      accountInitializedValidation,
      cardActiveValidation,
      sufficientLimitValidation,
      transactionFrequencyValidation,
      doubledTransactionValidation
    )
  }
}
