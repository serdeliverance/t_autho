package com.challenge.domain.validation.validations

import com.challenge.domain.validation.{AccountProvider, CARD_NOT_ACTIVE_MESSAGE, Failure, Success}
import com.challenge.stubs.AccountStubs
import org.mockito.MockitoSugar
import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.must.Matchers

class ActiveCardValidationSpec extends AnyFunSuite with Matchers with MockitoSugar with AccountStubs {

  private val accountProvider = mock[AccountProvider]
  private val validation      = new ActiveCardValidation()

  test("account not initialized validation success") {

    when(accountProvider.get()).thenReturn(Some(activatedAccount))

    val result = validation.validate(accountProvider, transaction)

    result mustBe Success(Some(activatedAccount))
  }

  test("account not initialized validation fails") {
    when(accountProvider.get()).thenReturn(Some(notInitializedAccount))

    val result = validation.validate(accountProvider, transaction)

    result mustBe Failure(None, List(CARD_NOT_ACTIVE_MESSAGE))
  }
}
