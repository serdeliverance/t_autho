package com.challenge.application.service

import com.challenge.application.utils.OperationResultOps.OptionAccountConverter
import com.challenge.domain.validation._
import com.challenge.domain.validation.validations.ValidationAggregator
import com.challenge.domain.{AccountRepository, OperationResult, Transaction}

import java.time.LocalDateTime

case class AuthorizeTransactionHandler(
  accountProvider: AccountProvider, // TODO ver que hacer con esto
  accountRepository: AccountRepository,
  validationAggregator: ValidationAggregator
) {

  def authorizeTransaction(merchant: String, amount: Int, time: LocalDateTime): OperationResult = {
    // TODO remember this value must be passed on demand to valiationAggregator who will pass around to the
    val transaction = Transaction(merchant, amount, time)

    validationAggregator.validate(accountProvider, transaction) match {
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
