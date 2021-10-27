package com.challenge.application.port.in

import com.challenge.domain.entity.OperationResult

import java.time.LocalDateTime

trait AuthorizeTransaction {
  def authorizeTransaction(merchant: String, amount: Int, time: LocalDateTime): OperationResult
}
