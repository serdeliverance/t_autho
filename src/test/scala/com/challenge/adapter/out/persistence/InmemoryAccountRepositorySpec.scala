package com.challenge.adapter.out.persistence

import com.challenge.domain.entity.{Account, Transaction}
import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.must.Matchers

class InmemoryAccountRepositorySpec extends AnyFunSuite with Matchers {

  test("create and retrieve account") {
    val inmemoryAccountRepository = new InmemoryAccountRepository

    inmemoryAccountRepository.create(true, 100)

    val result = inmemoryAccountRepository.get()

    result mustBe Some(Account(true, 100))
  }

  test("update account") {
    val inmemoryAccountRepository = new InmemoryAccountRepository

    inmemoryAccountRepository.create(true, 100)

    val result = inmemoryAccountRepository.update(Account(false, 10))

    result mustBe Account(false, 10)
  }
}
