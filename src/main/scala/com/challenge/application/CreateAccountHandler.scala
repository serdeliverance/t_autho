package com.challenge.application

import com.challenge.domain.validation.ACCOUNT_ALREADY_INITIALIZED_MESSAGE
import com.challenge.domain.validation.ValidationResult._
import com.challenge.domain.validation.precondition.Precondition
import com.challenge.domain.{AccountRepository, OperationResult}

class CreateAccountHandler(
  accountRepository: AccountRepository,
  precondition: Precondition
) {
  def createAccount(activeCard: Boolean, availableLimit: Int): OperationResult =
    precondition.evalPrecondition() match {
      case Failure(maybeAccount, violations) => OperationResult(maybeAccount, violations)
      case Success(maybeAccount) if maybeAccount.isEmpty =>
        val account = accountRepository.create(activeCard, availableLimit)
        OperationResult.success(account)
      // TODO remove hardcoded string
      case Success(maybeAccount) => OperationResult(maybeAccount, List(ACCOUNT_ALREADY_INITIALIZED_MESSAGE))
    }

}
