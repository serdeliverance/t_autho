package com.challenge.adapter.json

import com.challenge.adapter.command.{AuthorizeTransaction, Command, CreateAccount}
import com.challenge.adapter.json.input.{AuthorizeTransactionJson, CreateAccountJson}

object JsonInputOps {
  implicit class JsonInputDomainCommandConverter(jsonInput: JsonInput) {
    def toDomainCommand: Command = jsonInput match {
      case AuthorizeTransactionJson(transaction) =>
        AuthorizeTransaction(transaction.merchant, transaction.amount, transaction.time)
      case CreateAccountJson(account) =>
        CreateAccount(account.activeCard, account.availableLimit)
    }
  }
}
