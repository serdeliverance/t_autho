package com.challenge.domain.entity

import java.time.Instant

case class Account(activeCard: Boolean, availableLimit: Int, transactions: List[Transaction] = List.empty) {

  def process(transaction: Transaction): Account = this.copy(transactions = transaction :: this.transactions)

  def processN(transactionList: List[Transaction]): Account = this.copy(transactions = transactionList ++ transactions)

  def balance(): Int = transactions.map(_.amount).fold(availableLimit)(_ - _)

  def lastTransaction(): Option[Transaction] = this.transactions.sortBy(_.time)(Ordering[Instant]).reverse.headOption
}
