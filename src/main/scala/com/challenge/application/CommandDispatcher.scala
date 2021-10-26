package com.challenge.application

import com.challenge.domain._

class CommandDispatcher(
  createAccountHandler: CreateAccountHandler,
  authorizeTransactionHandler: AuthorizeTransactionHandler
) {

  def dispatch(command: Command): OperationResult = command match {
    case AccountCreation(activeCard, availableLimit) =>
      createAccountHandler.createAccount(activeCard, availableLimit)
    case AuthorizedTransaction(merchant, amount, time) =>
      authorizeTransactionHandler.authorizeTransaction(merchant, amount, time)
  }
}
