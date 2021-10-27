package com.challenge.application.port.out

import com.challenge.domain.entity.Account

trait AccountRepository {
  def get(): Option[Account]
  def create(activeCard: Boolean, availableLimit: Int): Account
  def update(updatedAccount: Account): Account
}
