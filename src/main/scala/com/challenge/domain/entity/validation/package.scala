package com.challenge.domain.entity.validation.validations

import com.challenge.domain.entity.Account

package object validation {

  type AccountProvider = () => Option[Account]

  val ACCOUNT_ALREADY_INITIALIZED_MESSAGE   = "account-already-initialized"
  val ACCOUNT_NOT_INITIALIZED_MESSAGE       = "account-not-initialized"
  val CARD_NOT_ACTIVE_MESSAGE               = "card-not-activate"
  val INSUFFICIENT_LIMIT_MESSAGE            = "insufficient-limit"
  val HIGH_FREQUENCY_SMALL_INTERVAL_MESSAGE = "high-frequency-small-interval"
  val DOUBLED_TRANSACTION_MESSAGE           = "doubled-transaction"
}
