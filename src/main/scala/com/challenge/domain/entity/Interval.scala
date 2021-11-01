package com.challenge.domain.entity

import java.time.Instant

case class Interval(from: Instant, to: Instant) {
  def isOnInterval(value: Instant) =
    value.isAfter(from) && value.isBefore(to)
}
