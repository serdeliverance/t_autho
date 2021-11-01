package com.challenge.domain.usecase

import com.challenge.application.port.in.AuthorizeTransactionService
import com.challenge.application.port.out.AccountRepository
import com.challenge.domain.entity.{OperationResult, Transaction}
import com.challenge.domain.entity.OperationResultOps.OptionAccountConverter
import com.challenge.domain.entity.validation.AccountProvider.defaultAccountProvider
import com.challenge.domain.entity.validation.validations.ValidationAggregator
import com.challenge.domain.entity.validation.{Failure, Success}

import java.time.Instant

case class AuthorizeTransactionUseCase(
  accountRepository: AccountRepository,
  validationAggregator: ValidationAggregator
) extends AuthorizeTransactionService {

  implicit val accountProvider = defaultAccountProvider(accountRepository)

  def authorizeTransaction(merchant: String, amount: Int, time: Instant): OperationResult = {
    val transaction = Transaction(merchant, amount, time)

    validationAggregator.validate(transaction) match {
      case Failure(maybeAccount, violations) => OperationResult.failure(maybeAccount, violations)
      case Success(_) =>
        accountRepository
          .get()
          .map { account =>
            val updatedAccount = account.process(transaction)
            accountRepository.update(updatedAccount)
          }
          .value
    }
  }

}
