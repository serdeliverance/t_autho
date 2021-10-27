package com.challenge.domain.entity

import java.time.LocalDateTime

case class Interval(from: LocalDateTime, to: LocalDateTime) {
  def isOnInterval(value: LocalDateTime) =
    value.isAfter(from) && value.isBefore(to)
}
