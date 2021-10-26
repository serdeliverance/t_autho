package com.challenge.application.dispatcher

import java.time.LocalDateTime

sealed trait Command

case class AccountCreation(activeCard: Boolean, availableLimit: Int)                 extends Command
case class AuthorizedTransaction(merchant: String, amount: Int, time: LocalDateTime) extends Command
