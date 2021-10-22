package com.nubank.domain

trait AccountRepository {
  def get(): Account
}
