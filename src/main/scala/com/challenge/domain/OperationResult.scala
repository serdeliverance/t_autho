package com.challenge.domain

case class OperationResult(account: Option[Account], violations: List[String])

object OperationResult {
  def success(account: Account)                         = OperationResult(Some(account), List.empty)
  def failureWithEmptyAccount(violations: List[String]) = OperationResult(None, violations)

  def apply(maybeAccount: Option[Account]): OperationResult = OperationResult(maybeAccount, List.empty)
}
