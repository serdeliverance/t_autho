package com.challenge.domain.usecase

import com.challenge.application.port.in.AuthorizeTransaction
import com.challenge.application.port.out.AccountRepository
import com.challenge.application.utils.OperationResultOps.OptionAccountConverter
import com.challenge.domain.entity.validation.AccountProvider.defaultAccountProvider
import com.challenge.domain.entity.validation.validations.ValidationAggregator
import com.challenge.domain.entity.validation.{Failure, Success}
import com.challenge.domain.entity
import com.challenge.domain.entity.{OperationResult, Transaction}

import java.time.LocalDateTime

case class AuthorizeTransactionUseCase(
  accountRepository: AccountRepository,
  validationAggregator: ValidationAggregator
) extends AuthorizeTransaction {

  implicit val accountProvider = defaultAccountProvider(accountRepository)

  def authorizeTransaction(merchant: String, amount: Int, time: LocalDateTime): OperationResult = {
    // TODO remember this value must be passed on demand to valiationAggregator who will pass around to the
    val transaction = Transaction(merchant, amount, time)

    validationAggregator.validate(transaction) match {
      case Failure(maybeAccount, violations) => entity.OperationResult(maybeAccount, violations)
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
