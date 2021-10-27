package com.challenge.domain.validation

import com.challenge.domain.Account

package object validation {
  type AccountProvider = () => Option[Account]
}
