package com.challenge.domain.usecase

import com.challenge.application.port.out.AccountRepository
import com.challenge.domain.entity.validation.AccountProvider.defaultAccountProvider
import com.challenge.domain.entity.validation._
import com.challenge.domain.entity.validation.validations.ValidationAggregator
import com.challenge.domain.entity.validation.validations.validation.INSUFFICIENT_LIMIT_MESSAGE
import com.challenge.domain.entity.{AccountBalance, OperationResult, Transaction}
import com.challenge.stubs.AccountStubs
import org.mockito.ArgumentMatchers.{any, eq => meq}
import org.mockito.MockitoSugar
import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.must.Matchers

import java.time.Instant

class AuthorizeTransactionUseCaseSpec extends AnyFunSuite with MockitoSugar with Matchers with AccountStubs {

  private val accountRepository        = mock[AccountRepository]
  private val validationAggregator     = mock[ValidationAggregator]
  implicit private val accountProvider = defaultAccountProvider(accountRepository)

  val authorizeAccountUseCase = AuthorizeTransactionUseCase(accountRepository, validationAggregator)

  test("if validations fails it should return operation result with violations") {
    val time = Instant.now

    val transaction = Transaction("Burger King", 30, time)

    when(validationAggregator.validate(meq(transaction))(any()))
      .thenReturn(Failure(Some(activatedAccount), List(INSUFFICIENT_LIMIT_MESSAGE)))

    val result = authorizeAccountUseCase.authorizeTransaction("Burger King", 30, time)

    result.violations mustBe List(INSUFFICIENT_LIMIT_MESSAGE)
  }

  test("if validations succeed it should process transaction and return operation result without violations") {
    val time = Instant.now

    val transaction    = Transaction("Burger King", 30, time)
    val updatedAccount = activatedAccount.process(transaction)

    when(validationAggregator.validate(meq(transaction))(any()))
      .thenReturn(Success())
    when(accountRepository.get()).thenReturn(Some(activatedAccount))
    when(accountRepository.update(updatedAccount)).thenReturn(updatedAccount)

    val result = authorizeAccountUseCase.authorizeTransaction("Burger King", 30, time)

    val expected = new OperationResult(Some(AccountBalance(true, 70)), List.empty)
    result mustBe expected
  }
}
