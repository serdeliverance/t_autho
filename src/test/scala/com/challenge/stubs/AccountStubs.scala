package com.challenge.stubs

import com.challenge.domain.entity.{Account, Transaction}

import java.time.{Instant, LocalDateTime}

trait AccountStubs {
  val activatedAccount = Account(true, 100)

  val notInitializedAccount = Account(false, 100)

  val accountWithLowLimit = Account(true, 5)

  val transaction = Transaction("burguer king", 10, Instant.now())

  val accountWithSomeTransactions = Account(true, 100, List(transaction, transaction))
}
