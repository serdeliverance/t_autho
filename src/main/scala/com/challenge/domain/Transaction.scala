package com.challenge.domain

import java.time.LocalDateTime

case class Transaction(merchant: String, amount: Int, time: LocalDateTime)
