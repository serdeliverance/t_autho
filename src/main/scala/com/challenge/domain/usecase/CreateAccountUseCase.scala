package com.challenge.domain.usecase

import com.challenge.application.port.in.CreateAccount
import com.challenge.application.port.out.AccountRepository
import com.challenge.domain.entity.validation.AccountProvider.defaultAccountProvider
import com.challenge.domain.entity.validation.validations.ValidationAggregator
import com.challenge.domain.entity.validation.{Failure, Success}
import com.challenge.domain.entity
import com.challenge.domain.entity.{EmptyTransaction, OperationResult}

case class CreateAccountUseCase(accountRepository: AccountRepository, validationAggregator: ValidationAggregator)
    extends CreateAccount {

  implicit val accountProvider = defaultAccountProvider(accountRepository)

  def createAccount(activeCard: Boolean, availableLimit: Int): OperationResult =
    validationAggregator.validate(EmptyTransaction) match {
      case Failure(maybeAccount, violations) => entity.OperationResult(maybeAccount, violations)
      case Success(_) =>
        val account = accountRepository.create(activeCard, availableLimit)
        OperationResult.success(account)
    }
}