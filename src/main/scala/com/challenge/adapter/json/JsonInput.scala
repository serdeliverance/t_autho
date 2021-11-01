package com.challenge.adapter.json

import java.time.Instant

object JsonInput {

  sealed trait JsonInput
  case class AuthorizeTransactionInput(merchant: String, amount: Int, time: Instant) extends JsonInput
  case class CreateAccountInput(activeCard: Boolean, availableLimit: Int)            extends JsonInput
}
