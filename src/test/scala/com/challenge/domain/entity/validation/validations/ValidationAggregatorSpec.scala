package com.challenge.domain.entity.validation.validations

import com.challenge.application.port.out.AccountRepository
import com.challenge.domain.entity.{Account, Transaction}
import com.challenge.domain.entity.validation.AccountProvider.defaultAccountProvider
import com.challenge.domain.entity.validation.validations.validation.{CARD_NOT_ACTIVE_MESSAGE, DOUBLED_TRANSACTION_MESSAGE, HIGH_FREQUENCY_SMALL_INTERVAL_MESSAGE, INSUFFICIENT_LIMIT_MESSAGE}
import com.challenge.domain.entity.validation.{Failure, Success}
import com.challenge.stubs.{AccountStubs, ValidationStubs}
import org.mockito.MockitoSugar
import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.must.Matchers

import java.time.Instant
import java.time.temporal.ChronoUnit

class ValidationAggregatorSpec
    extends AnyFunSuite
    with Matchers
    with MockitoSugar
    with ValidationStubs
    with AccountStubs {

  private val accountRepository        = mock[AccountRepository]
  implicit private val accountProvider = defaultAccountProvider(accountRepository)

  val validationAggregator = createValidationAggregator(2, 3, 2)

  test("transactions violate card not active and high frequency interval so aggregator must fail") {
    val transactions = createNTransactions(3)
    val account      = notInitializedAccount.processN(transactions)

    when(accountRepository.get()).thenReturn(Some(account))

    val newTransaction = Transaction("Mostaza", 10, Instant.now().minus(2, ChronoUnit.SECONDS))

    val result = validationAggregator.validate(newTransaction)

    result match {
      case Failure(_, violations) =>
        violations mustBe List(CARD_NOT_ACTIVE_MESSAGE, HIGH_FREQUENCY_SMALL_INTERVAL_MESSAGE)
      case Success(_) => fail()
    }
  }

  test("transactions violate sufficient limit and doubled transaction so aggregator must fail") {
    val firstTransaction = Transaction("Hoyts Cinema", 70, Instant.now.minus(30, ChronoUnit.SECONDS))
    val account          = Account(true, 100, List(firstTransaction))

    when(accountRepository.get()).thenReturn(Some(account))

    val newTransaction = firstTransaction.copy(time = Instant.now)

    val result = validationAggregator.validate(newTransaction)

    result match {
      case Failure(_, violations) =>
        violations mustBe List(INSUFFICIENT_LIMIT_MESSAGE, DOUBLED_TRANSACTION_MESSAGE)
      case Success(_) => fail()
    }
  }

  test("transaction does not fail any validation so it must succeed") {
    val transactions = createNTransactions(2)
    val account      = Account(true, 100, transactions)

    when(accountRepository.get()).thenReturn(Some(account))

    val newTransaction = Transaction("Mostaza", 10, Instant.now)

    val result = validationAggregator.validate(newTransaction)

    result mustBe Success()
  }
}
