package com.challenge.domain.validation.precondition

import com.challenge.domain.AccountRepository
import com.challenge.domain.validation.ACCOUNT_ALREADY_INITIALIZED_MESSAGE

case class AccountNotInitializedPrecondition(accountRepository: AccountRepository) extends Precondition {
  override val preCondition: () => Boolean = () => accountRepository.get().isEmpty
  override val errorMessage: String        = ACCOUNT_ALREADY_INITIALIZED_MESSAGE
}
