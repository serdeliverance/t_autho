package com.challenge.application.handler

import com.challenge.domain.validation.ValidationResult._
import com.challenge.domain.validation.precondition.Precondition
import com.challenge.domain.{AccountRepository, OperationResult}

case class CreateAccountHandler(
  accountRepository: AccountRepository,
  precondition: Precondition
) {
  def createAccount(activeCard: Boolean, availableLimit: Int): OperationResult =
    precondition.evalPrecondition() match {
      case Failure(maybeAccount, violations) => OperationResult(maybeAccount, violations)
      case Success(_) =>
        val account = accountRepository.create(activeCard, availableLimit)
        OperationResult.success(account)
    }

}
