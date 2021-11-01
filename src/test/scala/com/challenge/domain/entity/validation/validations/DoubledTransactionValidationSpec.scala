package com.challenge.domain.entity.validation.validations

import com.challenge.application.port.out.AccountRepository
import com.challenge.domain.entity.validation.validations.validation.DOUBLED_TRANSACTION_MESSAGE
import com.challenge.domain.entity.validation.{Failure, Success}
import com.challenge.domain.entity.{Account, Transaction}
import com.challenge.stubs.AccountStubs
import org.mockito.Mockito.when
import org.mockito.MockitoSugar.mock
import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.must.Matchers

import java.time.Instant
import java.time.temporal.ChronoUnit

class DoubledTransactionValidationSpec extends AnyFunSuite with Matchers with AccountStubs {

  private val accountRepository        = mock[AccountRepository]
  implicit private val accountProvider = () => accountRepository.get()

  private val validation = new DoubledTransactionValidation(2)

  test("two different transaction on window interval should pass doubled transaction validation") {
    val firstTransaction  = Transaction("Cinemark", 100, Instant.now)
    val secondTransaction = Transaction("Cinemark", 25, Instant.now.plus(1, ChronoUnit.MINUTES))

    when(accountRepository.get()).thenReturn(Some(Account(true, 100, List(firstTransaction))))

    val result = validation.validate(secondTransaction)

    result mustBe Success()
  }

  test("two equals transaction on window interval should violate doubled transaction validation") {
    val firstTransaction  = Transaction("Cinemark", 100, Instant.now.minus(1, ChronoUnit.MINUTES))
    val secondTransaction = firstTransaction.copy(time = firstTransaction.time.plus(1, ChronoUnit.MINUTES))

    val account = Account(true, 100, List(firstTransaction))

    when(accountRepository.get()).thenReturn(Some(account))

    val result = validation.validate(secondTransaction)

    result mustBe Failure(Some(account), List(DOUBLED_TRANSACTION_MESSAGE))
  }
}
