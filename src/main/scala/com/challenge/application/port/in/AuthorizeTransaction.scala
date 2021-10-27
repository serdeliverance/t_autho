package com.challenge.application.port.in

import com.challenge.domain.OperationResult

import java.time.LocalDateTime

trait AuthorizeTransaction {
  def authorizeTransaction(merchant: String, amount: Int, time: LocalDateTime): OperationResult
}
