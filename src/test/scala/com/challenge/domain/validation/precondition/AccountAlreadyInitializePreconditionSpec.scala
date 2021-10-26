package com.challenge.domain.validation.precondition

import com.challenge.domain.AccountRepository
import com.challenge.domain.validation.ACCOUNT_ALREADY_INITIALIZED_MESSAGE
import com.challenge.domain.validation.ValidationResult.{Failure, Success}
import com.challenge.stubs.AccountStubs
import org.junit.internal.runners.statements.Fail
import org.mockito.Mockito.when
import org.mockito.MockitoSugar.mock
import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.must.Matchers

class AccountAlreadyInitializePreconditionSpec extends AnyFunSuite with Matchers with AccountStubs {

  private val accountRepository = mock[AccountRepository]
  private val precondition      = new AccountAlreadyInitializePrecondition(accountRepository)

  test("account already initialized precondition success") {
    when(accountRepository.get()).thenReturn(Some(activatedAccount))

    val result = precondition.evalPrecondition()

    result mustBe Success()
  }

  test("account already initialized precondition fails") {
    when(accountRepository.get()).thenReturn(None)

    val result = precondition.evalPrecondition()

    result mustBe Failure(None, List(ACCOUNT_ALREADY_INITIALIZED_MESSAGE))
  }
}
