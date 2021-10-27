package com.challenge.domain.validation.validations

import com.challenge.domain.AccountRepository
import com.challenge.domain.validation.AccountProvider.defaultAccountProvider
import com.challenge.domain.validation.{CARD_NOT_ACTIVE_MESSAGE, Failure, Success}
import com.challenge.stubs.AccountStubs
import org.mockito.MockitoSugar
import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.must.Matchers

class ActiveCardValidationSpec extends AnyFunSuite with Matchers with MockitoSugar with AccountStubs {

  private val accountRepository        = mock[AccountRepository]
  implicit private val accountProvider = defaultAccountProvider(accountRepository)

  private val validation = new ActiveCardValidation()

  test("account not initialized validation success") {

    when(accountRepository.get()).thenReturn(Some(activatedAccount))

    val result = validation.validate(transaction)

    result mustBe Success(Some(activatedAccount))
  }

  test("account not initialized validation fails") {
    when(accountRepository.get()).thenReturn(Some(notInitializedAccount))

    val result = validation.validate(transaction)

    result mustBe Failure(None, List(CARD_NOT_ACTIVE_MESSAGE))
  }
}
