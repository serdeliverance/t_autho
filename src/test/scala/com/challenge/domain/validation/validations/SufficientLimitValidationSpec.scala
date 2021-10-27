package com.challenge.domain.validation.validations

import com.challenge.domain.validation._
import com.challenge.stubs.AccountStubs
import org.mockito.Mockito.when
import org.mockito.MockitoSugar
import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.must.Matchers

class SufficientLimitValidationSpec extends AnyFunSuite with Matchers with MockitoSugar with AccountStubs {

  private val accountProvider = mock[AccountProvider]
  private val validation      = new SufficientLimitValidation()

  test("sufficient limit validation success") {
    when(accountProvider.get()).thenReturn(Some(accountWithSomeTransactions))

    val result = validation.validate(accountProvider, transaction)

    result mustBe Success()
  }

  test("sufficient limit validation fails") {
    when(accountProvider.get()).thenReturn(Some(accountWithLowLimit))

    val result = validation.validate(accountProvider, transaction)

    result mustBe Failure(Some(accountWithLowLimit), List(INSUFFICIENT_LIMIT_MESSAGE))
  }
}
