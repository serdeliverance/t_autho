package com.challenge.application.utils

import com.challenge.domain.entity.{Account, OperationResult}

object OperationResultOps {
  implicit class OptionAccountConverter(maybeAccount: Option[Account]) {
    def value = OperationResult(maybeAccount)
  }
}
