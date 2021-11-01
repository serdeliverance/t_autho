package com.challenge.domain.entity

import com.challenge.domain.entity.AccountBalance.balanceFromAccount
import com.challenge.stubs.AccountStubs
import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.must.Matchers

class AccountBalanceSpec extends AnyFunSuite with Matchers with AccountStubs {
  test("account balance instantiation") {
    val result = balanceFromAccount(accountWithSomeTransactions)

    result mustBe AccountBalance(true, 80)
  }

  test("account balance instantiation when account has no transactions") {
    val result = balanceFromAccount(activatedAccount)

    result mustBe AccountBalance(true, 100)
  }
}
