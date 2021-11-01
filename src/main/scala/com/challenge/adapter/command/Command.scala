package com.challenge.adapter.command

import java.time.Instant

sealed trait Command

case class AuthorizeTransaction(merchant: String, amount: Int, time: Instant) extends Command
case class CreateAccount(activeCard: Boolean, availableLimit: Int)            extends Command
