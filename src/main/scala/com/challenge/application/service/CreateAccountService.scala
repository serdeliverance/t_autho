package com.challenge.application.service

import com.challenge.domain.validation._
import com.challenge.domain.validation.validations.ValidationAggregator
import com.challenge.domain.{AccountRepository, EmptyTransaction, OperationResult}

case class CreateAccountHandler(
  accountProvider: AccountProvider, // TODO ver que hacer con account provider... si dejarlo como una clase o como un type alias
  accountRepository: AccountRepository,
  validatorAggregator: ValidationAggregator
) {
  def createAccount(activeCard: Boolean, availableLimit: Int): OperationResult =
    validatorAggregator.validate(accountProvider, EmptyTransaction) match {
      case Failure(maybeAccount, violations) => OperationResult(maybeAccount, violations)
      case Success(_) =>
        val account = accountRepository.create(activeCard, availableLimit)
        OperationResult.success(account)
    }
}
