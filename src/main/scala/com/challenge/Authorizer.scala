package com.challenge

import cats.effect.{ExitCode, IO, IOApp}
import com.challenge.adapter.command.CommandHandler
import com.challenge.adapter.configuration.Configuration.loadConfiguration
import com.challenge.adapter.in.stdin.StdinReader
import com.challenge.adapter.out.persistence.InmemoryAccountRepository
import com.challenge.domain.entity.validation.validations._
import com.challenge.domain.usecase.{AuthorizeTransactionUseCase, CreateAccountUseCase}

object Authorizer extends IOApp {

  def createApp() =
    for {
      config <- loadConfiguration
      // persistence
      accountRepository = new InmemoryAccountRepository
      // validations
      accountNotInitializedValidation   = AccountNotInitializedValidation()
      createAccountValidationAggregator = ValidationAggregator(accountNotInitializedValidation)

      accountInitializedValidation = new AccountInitializedValidation()
      cardActiveValidation         = new ActiveCardValidation()
      sufficientLimitValidation    = new SufficientLimitValidation()
      transactionFrequencyValidation = new TransactionFrequencyValidation(
        config.highFrequencyInterval,
        config.highFrequencyMaxAllowed
      )
      doubledTransactionValidation = new DoubledTransactionValidation(
        config.doubledTransactionInterval,
        config.doubledTransactionMaxAllowed
      )

      authorizeValidationAggregator = ValidationAggregator(
        accountInitializedValidation,
        cardActiveValidation,
        sufficientLimitValidation,
        transactionFrequencyValidation,
        doubledTransactionValidation
      )

      // use case
      createAccountService    = CreateAccountUseCase(accountRepository, createAccountValidationAggregator)
      authorizeAccountService = AuthorizeTransactionUseCase(accountRepository, authorizeValidationAggregator)

      // command handler
      commandHandler = new CommandHandler(authorizeAccountService, createAccountService)

      // adapter in
      stdinReader = new StdinReader(commandHandler)
      _ <- stdinReader.read().compile.drain
    } yield ()

  override def run(args: List[String]): IO[ExitCode] =
    createApp().map(_ => ExitCode.Success)
}
