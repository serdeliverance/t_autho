package com.challenge.stubs

import com.challenge.domain.{Account, Transaction}

import java.time.LocalDateTime

trait AccountStubs {
  val activatedAccount = Account(true, 100)

  val notInitializedAccount = Account(false, 100)

  val accountWithLowLimit = Account(true, 5)

  val accountWithSomeTransactions = Account(true, 100, List(transaction, transaction))

  val transaction = Transaction("burguer king", 10, LocalDateTime.now())
}
