package com.challenge.application.handler

import com.challenge.application.utils.OperationResultOps.OptionAccountConverter
import com.challenge.domain.validation._
import com.challenge.domain.validation.validations.ValidationAggregator
import com.challenge.domain.{AccountRepository, OperationResult, Transaction}

import java.time.LocalDateTime

case class AuthorizeTransactionHandler(
  accountProvider: AccountProvider,
  accountRepository: AccountRepository,
//  precondition: Precondition, // near to be removed
//  validator: EntityValidationAggregator, // near to be removed
  validationAggregator: ValidationAggregator
) {

  def authorizeTransaction(merchant: String, amount: Int, time: LocalDateTime): OperationResult = {
    // TODO remember this value must be passed on demand to valiationAggregator who will pass around to the
    // different entity validation
    val transaction = Transaction(merchant, amount, time)

    // TODO move this to initialization o validationAggregator (and then remove precondition and validator from constructor)
//    val validationResult = validationAggregator.validateAll(
//      () => precondition.evalPrecondition(),
//      () =>
//        accountRepository
//          .get()
//          .map(account => validator.validate(account, transaction))
//          .getOrElse(ValidationResult.invalid(List("invalid account")))
//    )

    // TODO add validations on validateAll()
    validationAggregator.validate(accountProvider, transaction) match {
      case Failure(maybeAccount, violations) => OperationResult(maybeAccount, violations)
      case Success(_) =>
        accountRepository
          .get()
          .map { account =>
            val updatedAccount = account.process(transaction)
            accountRepository.update(updatedAccount)
          }
          .liftOperationResult()
    }
  }

}
