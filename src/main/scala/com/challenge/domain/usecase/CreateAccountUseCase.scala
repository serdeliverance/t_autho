package com.challenge.domain.usecase

import com.challenge.application.port.in.CreateAccountService
import com.challenge.application.port.out.AccountRepository
import com.challenge.domain.entity.validation.AccountProvider.defaultAccountProvider
import com.challenge.domain.entity.validation.validations.ValidationAggregator
import com.challenge.domain.entity.validation.{Failure, Success}
import com.challenge.domain.entity
import com.challenge.domain.entity._

case class CreateAccountUseCase(accountRepository: AccountRepository, validationAggregator: ValidationAggregator)
    extends CreateAccountService {

  implicit val accountProvider = defaultAccountProvider(accountRepository)

  def createAccount(activeCard: Boolean, availableLimit: Int): OperationResult =
    validationAggregator.validate(EmptyTransaction) match {
      case Failure(maybeAccount, violations) => OperationResult.failure(maybeAccount, violations)
      case Success(_) =>
        val account = accountRepository.create(activeCard, availableLimit)
        OperationResult.success(account)
    }
}
