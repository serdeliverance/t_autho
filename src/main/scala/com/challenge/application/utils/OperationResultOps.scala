package com.challenge.application.utils

import com.challenge.domain.validation.ValidationResult
import com.challenge.domain.validation.ValidationResult.{Success, ValidationResult}
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
      case ValidationResult.Failure(maybeAccount, violations) => OperationResult(maybeAccount, violations.toList)
      case Success(maybeAccount)                              => OperationResult(maybeAccount)
    }

  }
}
