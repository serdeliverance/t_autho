package com.challenge.domain.entity

import java.time.LocalDateTime

/**
  * Those operations are provided here in order to Transaction ADT as clean as possible
  */
object TransactionOps {
  implicit class TransactionExtensionMethods(transaction: Transaction) {
    def ==(otherTransaction: Transaction): Boolean = (transaction, otherTransaction) match {
      case (RegularTransaction(merchantA, amountA, _), RegularTransaction(merchantB, amountB, _)) =>
        merchantA == merchantB && amountA == amountB
      case _ => false
    }

    def time = transaction match {
      case RegularTransaction(_, _, time) => time
      case EmptyTransaction               => LocalDateTime.now() // This scenario won't happen. However, a dummy value is returned
    }

    def amount = transaction match {
      case RegularTransaction(_, amount, _) => amount
      case EmptyTransaction                 => 0
    }
  }
}
