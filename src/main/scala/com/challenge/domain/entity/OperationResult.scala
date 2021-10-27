package com.challenge.domain.entity

// TODO el operation result deberia retornar un AccountBalance(activeCard, availableLimit) para esto hay que refactorizar esta clase y crear la case class AccountBalance... tambien hay que refactorizar los smart constructors
case class OperationResult(account: Option[Account], violations: List[String])

object OperationResult {
  def success(account: Account)                         = OperationResult(Some(account), List.empty)
  def failureWithEmptyAccount(violations: List[String]) = OperationResult(None, violations)

  def apply(maybeAccount: Option[Account]): OperationResult = OperationResult(maybeAccount, List.empty)
}
