package com.challenge.adapter.command

import com.challenge.application.port.in.{AuthorizeTransactionService, CreateAccountService}
import com.challenge.domain.entity.OperationResult

case class CommandHandler(
  authorizeAccountService: AuthorizeTransactionService,
  createAccountService: CreateAccountService
) {
  def handle(command: Command): OperationResult = command match {
    case AuthorizeTransaction(merchant, amount, time) =>
      authorizeAccountService.authorizeTransaction(merchant, amount, time)
    case CreateAccount(activeCard, availableLimit) =>
      createAccountService.createAccount(activeCard, availableLimit)
  }
}
