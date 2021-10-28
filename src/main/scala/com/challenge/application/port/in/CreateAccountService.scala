package com.challenge.application.port.in

import com.challenge.domain.entity.OperationResult

trait CreateAccountService {
  def createAccount(activeCard: Boolean, availableLimit: Int): OperationResult
}
