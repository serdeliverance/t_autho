package com.challenge.stubs

import com.challenge.domain.{Account, Transaction}

import java.time.LocalDateTime

trait AccountStubs {
  val activatedAccount = Account(true, 100)

  val notInitializedAccount = Account(false, 100)

  val transaction = Transaction("burguer king", 10, LocalDateTime.now())
}
