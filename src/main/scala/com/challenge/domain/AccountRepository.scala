package com.challenge.domain

trait AccountRepository {
  def get(): Option[Account]
  def create(activeCard: Boolean, availableLimit: Int): Account
  def update(updatedAccount: Account): Account
}
