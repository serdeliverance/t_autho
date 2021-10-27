package com.challenge.domain

import org.scalatest.funsuite.AnyFunSuite

import java.time.LocalDateTime

class AccountSpec extends AnyFunSuite {
  // TODO process transaction

  test("calculate balance correctly") {
    val transactions = List(
      Transaction("burger king", 10, LocalDateTime.now().minusDays(2)),
      Transaction("Mc Donalds", 20, LocalDateTime.now().minusDays(2))
    )

    val account = Account(true, 100, transactions)

    val result = account.balance()

    assertResult(70)(result)
  }

  // TODO test last transaction

  // TODO test last transaction when account has not transaction
}
