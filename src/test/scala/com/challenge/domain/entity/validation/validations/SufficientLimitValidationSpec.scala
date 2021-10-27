package com.challenge.domain.entity.validation.validations

import com.challenge.application.port.out.AccountRepository
import com.challenge.domain.entity.validation.AccountProvider.defaultAccountProvider
import com.challenge.domain.entity.validation._
import com.challenge.stubs.AccountStubs
import org.mockito.MockitoSugar
import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.must.Matchers

class SufficientLimitValidationSpec extends AnyFunSuite with Matchers with MockitoSugar with AccountStubs {

  private val accountRepository        = mock[AccountRepository]
  implicit private val accountProvider = defaultAccountProvider(accountRepository)

  private val validation = new SufficientLimitValidation()

  test("sufficient limit validation success") {
    when(accountRepository.get()).thenReturn(Some(accountWithSomeTransactions))

    val result = validation.validate(transaction)

    result mustBe Success()
  }

  test("sufficient limit validation fails") {
    when(accountRepository.get()).thenReturn(Some(accountWithLowLimit))

    val result = validation.validate(transaction)

    result mustBe Failure(Some(accountWithLowLimit), List(INSUFFICIENT_LIMIT_MESSAGE))
  }
}
