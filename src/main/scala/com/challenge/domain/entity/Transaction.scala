package com.challenge.domain.entity

import java.time.Instant

sealed trait Transaction {
  def ==(other: Transaction): Boolean
  def time: Instant
  def amount: Int
}

case class RegularTransaction(merchant: String, amount: Int, time: Instant) extends Transaction {
  def ==(anotherTransaction: Transaction) = anotherTransaction match {
    case RegularTransaction(anotherMerchant, anotherAmount, _) => merchant == anotherMerchant && amount == anotherAmount
    case _                                                     => false
  }
}

case object EmptyTransaction extends Transaction {
  def ==(other: Transaction) = false
  def time                   = Instant.now()
  def amount                 = 0
}

object Transaction {
  def apply(merchant: String, amount: Int, time: Instant = Instant.now) =
    RegularTransaction(merchant, amount, time)
}
