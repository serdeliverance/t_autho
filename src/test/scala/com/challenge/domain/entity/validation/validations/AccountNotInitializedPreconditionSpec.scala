package com.challenge.domain.entity.validation.validations

import com.challenge.application.port.out.AccountRepository
import com.challenge.domain.entity.validation.AccountProvider.defaultAccountProvider
import com.challenge.domain.entity.validation._
import com.challenge.domain.entity.validation.validations.validation.ACCOUNT_ALREADY_INITIALIZED_MESSAGE
import com.challenge.stubs.AccountStubs
import org.mockito.Mockito.when
import org.mockito.MockitoSugar.mock
import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.must.Matchers

class AccountNotInitializedPreconditionSpec extends AnyFunSuite with Matchers with AccountStubs {

  private val accountRepository        = mock[AccountRepository]
  implicit private val accountProvider = defaultAccountProvider(accountRepository)

  private val validation = AccountNotInitializedValidation()

  test("account not initialized precondition success") {
    when(accountRepository.get()).thenReturn(None)

    val result = validation.validate(transaction)

    result mustBe Success()
  }

  test("account not initialized precondition fails") {
    when(accountRepository.get()).thenReturn(Some(activatedAccount))

    val result = validation.validate(transaction)

    result mustBe Failure(None, List(ACCOUNT_ALREADY_INITIALIZED_MESSAGE))
  }
}
