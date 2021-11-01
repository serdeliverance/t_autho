package com.challenge.domain.entity

import java.time.Instant

case class Account(activeCard: Boolean, availableLimit: Int, transactions: List[Transaction] = List.empty) {

  def process(transaction: Transaction): Account = this.copy(transactions = transaction :: this.transactions)

  def balance(): Int = transactions.map(_.amount).fold(availableLimit)(_ - _)

  implicit val instantOrdering: Ordering[Instant] = Ordering[Instant]

  def lastTransaction(): Option[Transaction] = this.transactions.sortBy(_.time).reverse.headOption
}
