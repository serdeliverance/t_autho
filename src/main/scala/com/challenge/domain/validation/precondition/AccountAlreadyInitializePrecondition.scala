package com.challenge.domain.validation.precondition

import com.challenge.domain.AccountRepository
import com.challenge.domain.validation.ACCOUNT_ALREADY_INITIALIZED_MESSAGE

class AccountAlreadyInitializePrecondition(accountRepository: AccountRepository) extends Precondition {
  override val preCondition: () => Boolean = () => accountRepository.get().nonEmpty
  override val errorMessage: String        = ACCOUNT_ALREADY_INITIALIZED_MESSAGE
}
