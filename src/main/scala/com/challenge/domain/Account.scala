package com.challenge.domain

case class Account(activeCard: Boolean, availableLimit: Int, transactions: List[Transaction] = List.empty) {
  def process(transaction: Transaction): Account = this.copy(transactions = transaction :: this.transactions)
  def balance(): Int                             = transactions.map(_.amount).fold(availableLimit)(_ - _)
}
