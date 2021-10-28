package com.challenge.adapter.command

import java.time.LocalDateTime

sealed trait Command

case class AuthorizeTransaction(merchant: String, amount: Int, time: LocalDateTime) extends Command
case class CreateAccount(activeCard: Boolean, availableLimit: Int)                  extends Command
