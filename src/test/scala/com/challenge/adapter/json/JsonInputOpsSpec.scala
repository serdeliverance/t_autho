package com.challenge.adapter.json

import com.challenge.adapter.command.{AuthorizeTransaction, CreateAccount}
import com.challenge.adapter.json.JsonInput.{AuthorizeTransactionInput, CreateAccountInput}
import com.challenge.adapter.json.JsonInputOps.JsonInputDomainCommandConverter
import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.must.Matchers

import java.time.Instant

class JsonInputOpsSpec extends AnyFunSuite with Matchers {

  test("create account input to create domain command") {
    val input = CreateAccountInput(true, 100)

    val result = input.toDomainCommand

    result mustBe CreateAccount(true, 100)
  }

  test("authorize transaction input to domain command") {
    val now   = Instant.now
    val input = AuthorizeTransactionInput("Fravega", 450, now)

    val result = input.toDomainCommand

    result mustBe AuthorizeTransaction("Fravega", 450, now)
  }
}
