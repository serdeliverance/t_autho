package com.challenge.domain.validation

trait Precondition {
  val preCondition: () => Boolean
  val errorMessage: String
  def evalPrecondition(): ValidationResult[Unit] = if (preCondition()) Right(()) else Left(List(errorMessage))
}
