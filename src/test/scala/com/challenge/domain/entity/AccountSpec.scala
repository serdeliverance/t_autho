package com.challenge.domain.entity

import com.challenge.stubs.AccountStubs
import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.must.Matchers

import java.time.Instant
import java.time.temporal.ChronoUnit

class AccountSpec extends AnyFunSuite with Matchers with AccountStubs {
  test("process transaction correctly") {
    val account = Account(true, 100)

    val transaction = Transaction("Mostaza", 50, Instant.now)

    val result = account.process(transaction)

    result mustBe Account(true, 100, List(transaction))
  }

  test("calculate balance correctly") {
    val transactions = List(
      Transaction("burger king", 10, Instant.now().minus(2, ChronoUnit.DAYS)),
      Transaction("Mc Donalds", 20, Instant.now().minus(2, ChronoUnit.DAYS))
    )

    val account = Account(true, 100, transactions)

    val result = account.balance()

    assertResult(70)(result)
  }

  test("retrieve last transaction") {
    val firstTransaction  = Transaction("burger king", 10, Instant.now().minus(4, ChronoUnit.DAYS))
    val secondTransaction = Transaction("Mc Donalds", 20, Instant.now().minus(2, ChronoUnit.DAYS))

    val transactions = List(
      firstTransaction,
      secondTransaction
    )

    val account = Account(true, 100, transactions)

    val result = account.lastTransaction()

    result mustBe Some(secondTransaction)
  }

  test("retrieve last transaction when account has no transactions") {
    val account = Account(true, 100)

    val result = account.lastTransaction()

    result mustBe None
  }
}
