package com.challenge.domain.validation

import com.challenge.domain.AccountRepository

object AccountProvider {
  def defaultAccountProvider(accountRepository: AccountRepository): AccountProvider =
    () => accountRepository.get()
}
