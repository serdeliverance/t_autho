package com.challenge.adapter.out.persistence

import com.challenge.application.port.out.AccountRepository
import com.challenge.domain.entity.Account

// TODO implement
class InmemoryAccountRepository extends AccountRepository {

  override def get(): Option[Account] = ???

  override def create(activeCard: Boolean, availableLimit: Int): Account = ???

  override def update(updatedAccount: Account): Account = ???
}
