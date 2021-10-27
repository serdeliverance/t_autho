package com.challenge.domain.entity

import java.time.LocalDateTime

sealed trait Transaction
case class RegularTransaction(merchant: String, amount: Int, time: LocalDateTime) extends Transaction

case object EmptyTransaction extends Transaction

object Transaction {
  def apply(merchant: String, amount: Int, time: LocalDateTime = LocalDateTime.now) =
    RegularTransaction(merchant, amount, time)
}
