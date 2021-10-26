package com.challenge.domain.validation.entityvalidation

import com.challenge.domain.validation.ACCOUNT_NOT_INITIALIZED_MESSAGE
import org.scalatest.funsuite.AnyFunSuite
import com.challenge.stubs.AccountStubs
import com.challenge.domain.validation.ValidationResult._
import org.scalatest.matchers.must.Matchers

class AccountNotInitializedValidationSpec extends AnyFunSuite with Matchers with AccountStubs {

  private val validation = new AccountNotInitializedValidation()

  test("account not initialized validation success") {
    val result = validation.validate(activatedAccount, transaction)

    result mustBe Success(Some(activatedAccount))
  }

  test("account not initialized validation fails") {
    val result = validation.validate(notInitializedAccount, transaction)

    result mustBe Failure(None, List(ACCOUNT_NOT_INITIALIZED_MESSAGE))
  }
}
