package com.challenge.domain.validation

import com.challenge.domain.validation.ValidationResult._
import com.challenge.domain.validation.validation.ActiveCardValidation
import com.challenge.domain.validation.validations.ActiveCardValidation
import com.challenge.stubs.AccountStubs
import org.mockito.Mockito.when
import org.mockito.MockitoSugar
import org.mockito.MockitoSugar.mock
import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.must.Matchers

class ActiveCardValidationSpec extends AnyFunSuite with Matchers with MockitoSugar with AccountStubs {

  private val validation      = new ActiveCardValidation()
  private val accountProvider = mock[AccountProvider]

  test("account not initialized validation success") {

    when(accountProvider.get()).thenReturn(Some(activatedAccount))

    val result = validation.validate(accountProvider, transaction)

    result mustBe Success(Some(activatedAccount))
  }

  test("account not initialized validation fails") {
    val result = validation.validate(notInitializedAccount, transaction)

    result mustBe Failure(None, List(CARD_NOT_ACTIVE_MESSAGE))
  }
}
