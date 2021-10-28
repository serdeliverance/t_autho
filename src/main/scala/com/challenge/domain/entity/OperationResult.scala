package com.challenge.domain.entity

import com.challenge.domain.entity.AccountBalance.balanceFromAccount

case class OperationResult(account: Option[AccountBalance], violations: List[String])

object OperationResult {

  def success(account: Account)                         = OperationResult(Some(balanceFromAccount(account)), List.empty)
  def failureWithEmptyAccount(violations: List[String]) = OperationResult(None, violations)

  def failure(account: Option[Account], violations: List[String]) =
    OperationResult(account.map(balanceFromAccount), violations)

  def apply(maybeAccount: Option[Account]): OperationResult =
    OperationResult(maybeAccount.map(balanceFromAccount), List.empty)
}
