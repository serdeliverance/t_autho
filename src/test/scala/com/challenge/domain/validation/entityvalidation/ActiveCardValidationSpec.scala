package com.challenge.domain.validation.entityvalidation

import com.challenge.domain.validation.CARD_NOT_ACTIVE_MESSAGE
import com.challenge.domain.validation.ValidationResult._
import com.challenge.stubs.AccountStubs
import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.must.Matchers

class ActiveCardValidationSpec extends AnyFunSuite with Matchers with AccountStubs {

  private val validation = new ActiveCardValidation()

  test("account not initialized validation success") {
    val result = validation.validate(activatedAccount, transaction)

    result mustBe Success(Some(activatedAccount))
  }

  test("account not initialized validation fails") {
    val result = validation.validate(notInitializedAccount, transaction)

    result mustBe Failure(None, List(CARD_NOT_ACTIVE_MESSAGE))
  }
}
