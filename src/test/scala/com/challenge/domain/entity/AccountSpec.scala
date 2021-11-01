package com.challenge.domain.entity

import org.scalatest.funsuite.AnyFunSuite

import java.time.Instant
import java.time.temporal.ChronoUnit

class AccountSpec extends AnyFunSuite {
  // TODO process transaction

  test("calculate balance correctly") {
    val transactions = List(
      Transaction("burger king", 10, Instant.now().minus(2, ChronoUnit.DAYS)),
      Transaction("Mc Donalds", 20, Instant.now().minus(2, ChronoUnit.DAYS))
    )

    val account = Account(true, 100, transactions)

    val result = account.balance()

    assertResult(70)(result)
  }

  // TODO test last transaction

  // TODO test last transaction when account has not transaction
}
