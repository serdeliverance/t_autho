package com.challenge.domain

import cats.Functor

case class OperationResult[A](entity: Option[A], violations: List[String]) {
  def flatMap[B](f: A => OperationResult[B]): OperationResult[B] =
    if (violations.nonEmpty) this
    else entity.f
}

object OperationResult {
  def success(account: Account) = OperationResult(Some(account), List.empty)

  def failureWithEmptyAccount(violations: List[String]) = OperationResult(None, violations)
}
