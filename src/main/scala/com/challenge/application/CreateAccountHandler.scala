package com.challenge.application

import com.challenge.domain.{AccountRepository, OperationResult}
import com.challenge.domain.validation.{Precondition, ValidationAggregator}
import com.challenge.application.utils.ValidationResultOps._

class CreateAccountHandler(
  accountRepository: AccountRepository,
  precondition: Precondition
) {
  def createAccount(activeCard: Boolean, availableLimit: Int) = {
    val validationResult = precondition.evalPrecondition()

    if (validationResult.isValid()) {
      val account = accountRepository.create(activeCard, availableLimit)
      OperationResult.success(account)
    } else OperationResult.failureWithEmptyAccount(validationResult.left.getOrElse(List.empty))
  }

}
