package com.challenge.domain

import java.time.LocalDateTime

sealed trait Transaction
case class RegularTransaction(merchant: String, amount: Int, time: LocalDateTime) extends Transaction
case object EmptyTransaction                                                      extends Transaction

object Transaction {
  // TODO complete
  def apply(merchant: String, amount: Int, time: LocalDateTime = LocalDateTime.now) =
    RegularTransaction("", 999, time)
}
