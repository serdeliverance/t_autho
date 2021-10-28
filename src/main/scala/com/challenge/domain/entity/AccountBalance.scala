package com.challenge.domain.entity

case class AccountBalance(activeCard: Boolean, availableLimit: Int)

object AccountBalance {
  def balanceFromAccount(account: Account): AccountBalance = AccountBalance(account.activeCard, account.balance())
}
