package com.challenge.application.service

import com.challenge.application.utils.OperationResultOps.OptionAccountConverter
import com.challenge.domain.validation.AccountProvider.defaultAccountProvider
import com.challenge.domain.validation._
import com.challenge.domain.validation.validations.ValidationAggregator
import com.challenge.domain.{AccountRepository, OperationResult, Transaction}

import java.time.LocalDateTime

case class AuthorizeTransactionHandler(
  accountRepository: AccountRepository,
  validationAggregator: ValidationAggregator
) {

  implicit val accountProvider = defaultAccountProvider(accountRepository)

  def authorizeTransaction(merchant: String, amount: Int, time: LocalDateTime): OperationResult = {
    // TODO remember this value must be passed on demand to valiationAggregator who will pass around to the
    val transaction = Transaction(merchant, amount, time)

    validationAggregator.validate(transaction) match {
      case Failure(maybeAccount, violations) => OperationResult(maybeAccount, violations)
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
