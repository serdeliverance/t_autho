package com.challenge.domain

package object validation {
  type ValidationResult[T] = Either[List[String], T]
}
