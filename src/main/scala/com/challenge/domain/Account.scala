package com.challenge.domain

case class Account(activeCard: Boolean, availableLimit: Int, transactions: List[Transaction]) {
  def process(transaction: Transaction): Account = this.copy(transactions = transaction :: this.transactions)
}
