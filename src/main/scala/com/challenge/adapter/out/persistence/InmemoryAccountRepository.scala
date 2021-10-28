package com.challenge.adapter.out.persistence

import com.challenge.application.port.out.AccountRepository
import com.challenge.domain.entity.Account

/**
  * To make it simple, this inmemory repository is backed by a mutable data structure.
  *
  * Create and update produces side effects, but It was on purpose to keep this implementation simple.
  *
  */
class InmemoryAccountRepository extends AccountRepository {

  private var account: Option[Account] = None

  def get(): Option[Account] = account

  def create(activeCard: Boolean, availableLimit: Int): Account = {
    account = Some(Account(activeCard, availableLimit))
    account.get // It is a bad practice. I use this here just for readability
  }

  def update(updatedAccount: Account): Account = {
    account = Some(updatedAccount)
    updatedAccount
  }
}
