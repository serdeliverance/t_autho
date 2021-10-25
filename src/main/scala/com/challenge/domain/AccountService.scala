package com.challenge.domain

import com.challenge.application.utils.AccountOps.AccountToOperationResult
import com.challenge.domain.validation.{Precondition, ValidationAggregator}
import com.challenge.application.utils.OperationResultOps._

class AccountService(
  accountRepository: AccountRepository,
  precondition: Precondition,
  validator: ValidationAggregator
) {
  def createAccount(activeCard: Boolean, availableLimit: Int) =
    for {
      _      <- precondition.evalPrecondition().liftOperationResult()
      result <- accountRepository.create(activeCard, availableLimit).liftEither
    } yield result
}
