package com.challenge.domain.validation.entityvalidation

import com.challenge.domain.validation.INSUFFICIENT_LIMIT_MESSAGE
import com.challenge.domain.validation.ValidationResult.{Failure, Success}
import com.challenge.stubs.AccountStubs
import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.must.Matchers

class SufficientLimitValidationSpec extends AnyFunSuite with Matchers with AccountStubs {

  private val validation = new SufficientLimitValidation()

  test("sufficient limit validation success") {
    val result = validation.validate(accountWithSomeTransactions, transaction)

    result mustBe Success()
  }

  test("sufficient limit validation fails") {
    val result = validation.validate(accountWithLowLimit, transaction)

    result mustBe Failure(Some(accountWithLowLimit), List(INSUFFICIENT_LIMIT_MESSAGE))
  }
}
