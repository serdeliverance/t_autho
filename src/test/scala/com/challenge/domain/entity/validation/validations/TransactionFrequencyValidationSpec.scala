package com.challenge.domain.entity.validation.validations

import com.challenge.application.port.out.AccountRepository
import com.challenge.domain.entity.validation.AccountProvider.defaultAccountProvider
import com.challenge.domain.entity.validation._
import com.challenge.domain.entity.validation.validations.validation.HIGH_FREQUENCY_SMALL_INTERVAL_MESSAGE
import com.challenge.domain.entity.{Account, Transaction}
import com.challenge.stubs.AccountStubs
import org.mockito.MockitoSugar
import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.must.Matchers

import java.time.Instant

class TransactionFrequencyValidationSpec extends AnyFunSuite with MockitoSugar with Matchers with AccountStubs {

  private val accountRepository        = mock[AccountRepository]
  implicit private val accountProvider = defaultAccountProvider(accountRepository)

  private val validation = new TransactionFrequencyValidation(2, 3)

  test("transaction that violates high frequency validation must fail") {
    val initialTransactions = createNTransactions(3)
    val account             = Account(true, 100, initialTransactions)

    val newTransaction = Transaction("Mostaza", 10, Instant.now)

    when(accountRepository.get()).thenReturn(Some(account))

    val result = validation.validate(newTransaction)

    result mustBe Failure(Some(account), List(HIGH_FREQUENCY_SMALL_INTERVAL_MESSAGE))
  }

  test("transaction that not violates high frequency validation must not fail") {
    val initialTransactions = createNTransactions(2)
    val account             = Account(true, 100, initialTransactions)

    val newTransaction = Transaction("Mostaza", 10, Instant.now)

    when(accountRepository.get()).thenReturn(Some(account))

    val result = validation.validate(newTransaction)

    result mustBe Success()
  }
}
