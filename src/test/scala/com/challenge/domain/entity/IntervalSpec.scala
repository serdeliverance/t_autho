package com.challenge.domain.entity

import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.must.Matchers

import java.time.Instant
import java.time.temporal.ChronoUnit

class IntervalSpec extends AnyFunSuite with Matchers {

  test("instant is on interval") {
    val now              = Instant.now()
    val twoMinutesBefore = now.minus(2, ChronoUnit.MINUTES)

    val instantOnInterval = now.minus(1, ChronoUnit.MINUTES)

    val interval = Interval(twoMinutesBefore, now)

    val result = interval.isOnInterval(instantOnInterval)

    result mustBe true
  }

  test("instant is not on interval") {
    val now               = Instant.now()
    val twoMinutesBefore  = now.minus(2, ChronoUnit.MINUTES)
    val threeMinutsBefore = now.minus(3, ChronoUnit.MINUTES)

    val interval = Interval(threeMinutsBefore, twoMinutesBefore)

    val result = interval.isOnInterval(now)

    result mustBe false
  }
}
