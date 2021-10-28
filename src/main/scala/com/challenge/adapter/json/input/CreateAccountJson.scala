package com.challenge.adapter.json.input

import com.challenge.adapter.json.JsonInput
import com.challenge.adapter.json.input.CreateAccountJson.AccountInfo

case class CreateAccountJson(account: AccountInfo) extends JsonInput

object CreateAccountJson {
  case class AccountInfo(activeCard: Boolean, availableLimit: Int)
}
