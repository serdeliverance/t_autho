package com.challenge.domain.usecase

import com.challenge.application.port.out.AccountRepository
import com.challenge.domain.entity.validation.AccountProvider.defaultAccountProvider
import com.challenge.domain.entity.validation.validations.ValidationAggregator
import com.challenge.domain.entity.validation.validations.validation.ACCOUNT_ALREADY_INITIALIZED_MESSAGE
import com.challenge.domain.entity.validation.{Failure, Success}
import com.challenge.domain.entity.{AccountBalance, EmptyTransaction, OperationResult}
import com.challenge.stubs.AccountStubs
import org.mockito.ArgumentMatchers.{any, eq => meq}
import org.mockito.MockitoSugar
import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.must.Matchers

import java.time.Instant

class CreateAccountUseCaseSpec extends AnyFunSuite with MockitoSugar with Matchers with AccountStubs {

  private val accountRepository        = mock[AccountRepository]
  private val validationAggregator     = mock[ValidationAggregator]
  implicit private val accountProvider = defaultAccountProvider(accountRepository)

  val createAccountUseCase = CreateAccountUseCase(accountRepository, validationAggregator)

  test("if validations fails it should return operation result with violations") {
    val transaction = EmptyTransaction

    when(validationAggregator.validate(meq(transaction))(any()))
      .thenReturn(Failure(None, List(ACCOUNT_ALREADY_INITIALIZED_MESSAGE)))

    val result = createAccountUseCase.createAccount(true, 100)

    result.violations mustBe List(ACCOUNT_ALREADY_INITIALIZED_MESSAGE)
  }

  test("if validations succeed it should process transaction and return operation result without violations") {
    val time = Instant.now

    val transaction = EmptyTransaction

    when(validationAggregator.validate(meq(transaction))(any()))
      .thenReturn(Success())
    when(accountRepository.create(true, 100)).thenReturn(activatedAccount)

    val result = createAccountUseCase.createAccount(true, 100)

    val expected = new OperationResult(Some(AccountBalance(true, 100)), List.empty)
    result mustBe expected
  }
}
