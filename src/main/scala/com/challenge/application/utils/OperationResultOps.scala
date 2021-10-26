package com.challenge.application.utils

import com.challenge.domain.validation.ValidationResult._
import com.challenge.domain.{Account, OperationResult}

object OperationResultOps {
  implicit class EitherConverter(result: Either[List[String], Account]) {
    def liftOperationResult(account: Account) = result match {
      case Left(violations)      => OperationResult(Some(account), violations)
      case Right(accountUpdated) => OperationResult.success(accountUpdated)
    }
  }

  implicit class ValidationResultConverter(validationResult: ValidationResult) {
    def liftOperationResult() = validationResult match {
      case Failure(maybeAccount, violations) => OperationResult(maybeAccount, violations)
      case Success(maybeAccount)             => OperationResult(maybeAccount)
    }
  }

  implicit class OptionAccountConverter(maybeAccount: Option[Account]) {
    def liftOperationResult() = OperationResult(maybeAccount)
  }
}
