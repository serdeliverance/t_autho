package com.nubank.domain.validation

trait Precondition {
  val preCondition: () => Boolean
  val errorMessage: String
  def evalPrecondition(): PreconditionResult = if (preCondition()) Failure(errorMessage) else Success
}
