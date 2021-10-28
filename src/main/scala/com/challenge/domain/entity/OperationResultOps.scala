package com.challenge.domain.entity

object OperationResultOps {
  implicit class OptionAccountConverter(maybeAccount: Option[Account]) {
    def value = OperationResult(maybeAccount)
  }
}
