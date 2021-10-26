package com.challenge.application

import com.challenge.domain.validation.{EntityValidationAggregator, Precondition, ValidationAggregator, ValidationResult}
import com.challenge.domain.{AccountRepository, OperationResult, Transaction}
import com.challenge.application.utils.ValidationResultOps._

import java.time.LocalDateTime

class AuthorizeTransactionHandler(
  accountRepository: AccountRepository,
  precondition: Precondition,
  validator: EntityValidationAggregator,
  validationAggregator: ValidationAggregator
) {

  def authorizeTransaction(merchant: String, amount: Int, time: LocalDateTime): OperationResult = {
    val validationResult = validationAggregator.validateAll(
      () => precondition.evalPrecondition(),
      () =>
        accountRepository
          .get()
          .map(account => validator.validate(account, Transaction(merchant, amount, time)))
          .getOrElse(ValidationResult.invalid("invalid account"))
    )

    if (validationResult.isNotValid()) OperationResult.validationResult
  }

}
