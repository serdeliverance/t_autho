package com.challenge.application

import com.challenge.domain.{AccountCreation, AccountService, AuthorizedTransaction, Command, OperationResult}

class CommandDispatcher(accountService: AccountService) {

  def dispath(command: Command): OperationResult = command match {
    case AccountCreation(activeCard, availableLimit) =>
      accountService.createAccount(activeCard, availableLimit).liftOperationResult
    case AuthorizedTransaction(merchant, amount, time) => ???
  }
}
