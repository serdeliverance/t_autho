package com.challenge.application.port.in

import com.challenge.domain.OperationResult

trait CreateAccount {
  def createAccount(activeCard: Boolean, availableLimit: Int): OperationResult
}
