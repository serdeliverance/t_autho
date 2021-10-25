package com.challenge.application.utils

import com.challenge.domain.{Account, OperationResult}

object AccountOps {
  implicit class AccountToOperationResult(account: Account) {
    def liftEither: OperationResult[Account] = OperationResult.success(account)
  }
}
