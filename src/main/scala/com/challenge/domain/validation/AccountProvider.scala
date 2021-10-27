package com.challenge.domain.validation

import com.challenge.domain.Account

trait AccountProvider {
  def get(): Option[Account]
}
