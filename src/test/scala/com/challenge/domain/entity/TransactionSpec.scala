package com.challenge.domain.entity

import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.must.Matchers

import java.time.Instant
import java.time.temporal.ChronoUnit

class TransactionSpec extends AnyFunSuite with Matchers {
  test("two transaction with same merchant and amount must be equal ") {
    val transaction        = Transaction("Heisenburguer", 100, Instant.now)
    val anotherTransaction = Transaction("Heisenburguer", 100, Instant.now.minus(3, ChronoUnit.HOURS))

    val result = transaction == anotherTransaction

    result mustBe true
  }

  test("two transaction with different merchant or amount must be not equal ") {
    val transaction        = Transaction("Heisenburguer", 100, Instant.now)
    val anotherTransaction = Transaction("Heisenburguer", 75, Instant.now.minus(3, ChronoUnit.HOURS))

    val result = transaction == anotherTransaction

    result mustBe false
  }

  test("two transaction of different type must not be equal ") {
    val transaction: Transaction        = RegularTransaction("Heisenburguer", 100, Instant.now)
    val anotherTransaction: Transaction = EmptyTransaction

    val result = transaction == anotherTransaction

    result mustBe false
  }

  test("transaction must return time correctly") {
    val time = Instant.now()

    val transaction = Transaction("Lo de Carlitos", 300, time)

    val result = transaction.time

    result mustBe time
  }

  test("transaction must return amount") {
    val transaction = Transaction("Burger King", 100, Instant.now)

    val result = transaction.amount

    result mustBe 100
  }

  test("empty transaction must return zero amount") {
    val transaction = EmptyTransaction

    val result = transaction.amount

    result mustBe 0
  }
}
