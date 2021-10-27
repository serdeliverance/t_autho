package com.challenge.application.service

import com.challenge.domain.validation.AccountProvider.defaultAccountProvider
import com.challenge.domain.validation._
import com.challenge.domain.validation.validations.ValidationAggregator
import com.challenge.domain.{AccountRepository, EmptyTransaction, OperationResult}

case class CreateAccountHandler(accountRepository: AccountRepository, validationAggregator: ValidationAggregator) {

  implicit val accountProvider = defaultAccountProvider(accountRepository)

  def createAccount(activeCard: Boolean, availableLimit: Int): OperationResult =
    validationAggregator.validate(EmptyTransaction) match {
      case Failure(maybeAccount, violations) => OperationResult(maybeAccount, violations)
      case Success(_) =>
        val account = accountRepository.create(activeCard, availableLimit)
        OperationResult.success(account)
    }
}
