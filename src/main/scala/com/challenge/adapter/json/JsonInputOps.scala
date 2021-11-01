package com.challenge.adapter.json

import com.challenge.adapter.command.{AuthorizeTransaction, Command, CreateAccount}
import com.challenge.adapter.json.JsonInput.{AuthorizeTransactionInput, CreateAccountInput, JsonInput}

object JsonInputOps {
  implicit class JsonInputDomainCommandConverter(jsonInput: JsonInput) {
    def toDomainCommand: Command = jsonInput match {
      case AuthorizeTransactionInput(merchant, amount, time) =>
        AuthorizeTransaction(merchant, amount, time)
      case CreateAccountInput(activeCard, availableLimit) =>
        CreateAccount(activeCard, availableLimit)
    }
  }
}
