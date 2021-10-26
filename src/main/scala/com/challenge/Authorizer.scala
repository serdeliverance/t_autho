package com.challenge

import cats.effect.{ExitCode, IO, IOApp}
import com.challenge.application.dispatcher.CommandDispatcher
import com.challenge.application.handler.{AuthorizeTransactionHandler, CreateAccountHandler}
import com.challenge.domain.validation.precondition.AccountNotInitializedPrecondition
import com.challenge.infrastructure.repository.InmemoryAccountRepository

object Authorizer extends IOApp {
  override def run(args: List[String]): IO[ExitCode] = {

    val accountRepository = new InmemoryAccountRepository

    val createAccountPrecondition = AccountNotInitializedPrecondition(accountRepository)

    val createAccountHandler        = CreateAccountHandler(accountRepository, createAccountPrecondition)
    val authorizeTransactionHandler = AuthorizeTransactionHandler(accountRepository, null)

    val commandDispatcher = CommandDispatcher(createAccountHandler, authorizeTransactionHandler)

    IO(ExitCode.Success)
  }
}
