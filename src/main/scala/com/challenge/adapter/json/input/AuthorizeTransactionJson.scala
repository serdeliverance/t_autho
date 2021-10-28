package com.challenge.adapter.json.input

import com.challenge.adapter.json.JsonInput
import com.challenge.adapter.json.input.AuthorizeTransactionJson.TransactionInfo

import java.time.LocalDateTime

case class AuthorizeTransactionJson(transaction: TransactionInfo) extends JsonInput

object AuthorizeTransactionJson {
  case class TransactionInfo(merchant: String, amount: Int, time: LocalDateTime)
}
