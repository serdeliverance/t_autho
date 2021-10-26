package com.challenge.domain.validation.entityvalidation

import com.challenge.domain.validation.CARD_NOT_ACTIVE_MESSAGE
import com.challenge.domain.validation.ValidationResult._
import com.challenge.domain.{Account, Transaction}

class ActiveCardValidation() extends EntityValidation {

  def validate(account: Account, transaction: Transaction): ValidationResult =
    if (account.activeCard) Success(Some(account)) else Failure(None, List(CARD_NOT_ACTIVE_MESSAGE))
}
