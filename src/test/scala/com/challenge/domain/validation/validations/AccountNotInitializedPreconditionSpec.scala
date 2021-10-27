package com.challenge.domain.validation.validations

import com.challenge.domain.validation._
import com.challenge.stubs.AccountStubs
import org.mockito.Mockito.when
import org.mockito.MockitoSugar.mock
import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.must.Matchers

class AccountNotInitializedPreconditionSpec extends AnyFunSuite with Matchers with AccountStubs {

  private val accountProvider = mock[AccountProvider]
  private val validation      = new AccountNotInitializedValidation()

  test("account not initialized precondition success") {
    when(accountProvider.get()).thenReturn(None)

    val result = validation.validate(accountProvider, transaction)

    result mustBe Success()
  }

  test("account not initialized precondition fails") {
    when(accountProvider.get()).thenReturn(Some(activatedAccount))

    val result = validation.validate(accountProvider, transaction)

    result mustBe Failure(None, List(ACCOUNT_ALREADY_INITIALIZED_MESSAGE))
  }
}