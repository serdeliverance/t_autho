package com.challenge.stubs

import com.challenge.domain.entity.{Account, Transaction}

import java.time.Instant
import java.time.temporal.ChronoUnit

trait AccountStubs {
  val activatedAccount = Account(true, 100)

  val notInitializedAccount = Account(false, 100)

  val accountWithLowLimit = Account(true, 5)

  val transaction = Transaction("burguer king", 10, Instant.now())

  def createNTransactions(quantity: Int): List[Transaction] =
    (1 to quantity).map(i => Transaction(s"merchant ${i}", 10, Instant.now.minus(30 * i, ChronoUnit.SECONDS))).toList

  val accountWithSomeTransactions = Account(true, 100, List(transaction, transaction))
}
