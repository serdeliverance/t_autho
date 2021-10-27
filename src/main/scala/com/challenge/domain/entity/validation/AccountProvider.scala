package com.challenge.domain.entity.validation

import com.challenge.application.port.out.AccountRepository
import com.challenge.domain.entity.validation.validations.validation.AccountProvider

object AccountProvider {
  def defaultAccountProvider(accountRepository: AccountRepository): AccountProvider =
    () => accountRepository.get()
}
