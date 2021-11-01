package com.challenge.adapter.json

import com.challenge.adapter.json.JsonInput.{AuthorizeTransactionInput, CreateAccountInput, JsonInput}
import com.challenge.adapter.json.JsonParsing.{jsonInputDecoder, operationResultEncoder}
import com.challenge.domain.entity.OperationResult
import com.challenge.stubs.AccountStubs
import io.circe.parser.decode
import io.circe.syntax._
import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.must.Matchers

import java.time.Instant

class JsonParsingSpec extends AnyFunSuite with Matchers with AccountStubs {

  test("decode create account json input successfully") {
    val input =
      """
        |{
        |   "account":{
        |      "active-card":false,
        |      "available-limit":100
        |   }
        |}
        |""".stripMargin

    val result = decode[JsonInput](input)

    result mustBe Right(CreateAccountInput(false, 100))
  }

  test("decode authorize transaction json input successfully") {
    val input =
      """
        |{
        |   "transaction":{
        |      "merchant":"Burger King",
        |      "amount":20,
        |      "time":"2019-02-13T11:00:00.000Z"
        |   }
        |}
        |""".stripMargin

    val result = decode[JsonInput](input)

    result mustBe Right(AuthorizeTransactionInput("Burger King", 20, Instant.parse("2019-02-13T11:00:00.000Z")))
  }

  test("encode operation result successfully") {
    val operationResult = OperationResult(Some(activatedAccount))

    val result = operationResult.asJson.noSpaces

    val expected = """{"account":{"active-card":true,"available-limit":100},"violations":[]}"""

    result mustBe expected
  }

}
