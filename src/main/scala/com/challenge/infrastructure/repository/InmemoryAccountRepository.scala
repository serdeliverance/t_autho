package com.challenge.infrastructure.repository

import com.challenge.domain.{Account, AccountRepository}

class InmemoryAccountRepository extends AccountRepository {

  // TODO implement
  override def get(): Option[Account] = ???

  override def create(activeCard: Boolean, availableLimit: Int): Account = ???

  override def update(updatedAccount: Account): Account = ???
}
