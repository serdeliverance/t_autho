package com.challenge.application.utils

import com.challenge.domain.validation.ValidationResult
import com.challenge.domain.{Account, OperationResult}

object OperationResultOps {
  implicit class ResultToOperationResult(result: Either[List[String], Account]) {
    def liftOperationResult(account: Account) = result match {
      case Left(violations)      => OperationResult(Some(account), violations)
      case Right(accountUpdated) => OperationResult.success(accountUpdated)
    }
  }

  implicit class ValidatioResultToOperationResult(validationResult: ValidationResult[Any]) {
    def liftOperationResult(account: Option[Account] = None) =
      OperationResult(account, validationResult.left.getOrElse(List.empty))
  }
}
