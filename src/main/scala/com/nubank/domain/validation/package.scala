package com.nubank.domain

package object validation {
  type ValidationResult = Either[List[String], Account]

  sealed trait PreconditionResult
  case class Failure(message: String) extends PreconditionResult
  case object Success                 extends PreconditionResult
}
