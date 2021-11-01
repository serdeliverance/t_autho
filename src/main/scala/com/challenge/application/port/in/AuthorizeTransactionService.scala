package com.challenge.application.port.in

import com.challenge.domain.entity.OperationResult

import java.time.Instant

trait AuthorizeTransactionService {
  def authorizeTransaction(merchant: String, amount: Int, time: Instant): OperationResult
}
