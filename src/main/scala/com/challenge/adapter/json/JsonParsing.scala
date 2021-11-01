package com.challenge.adapter.json

import com.challenge.adapter.json.JsonFields._
import com.challenge.adapter.json.JsonInput._
import com.challenge.domain.entity.{AccountBalance, OperationResult}
import io.circe.Decoder.Result
import io.circe._
import io.circe.generic.extras.Configuration
import io.circe.generic.extras.semiauto.deriveConfiguredEncoder

import java.time.Instant
import scala.util.Try

object JsonParsing {

  implicit val customPrinter: Printer      = Printer.noSpaces.copy(dropNullValues = true)
  implicit val customConfig: Configuration = Configuration.default.withKebabCaseMemberNames

  implicit val operationResultEncoder: Encoder[OperationResult] = deriveConfiguredEncoder
  implicit val accountBalanceEncoder: Encoder[AccountBalance]   = deriveConfiguredEncoder

  implicit val instantDecoder: Decoder[Instant] = Decoder.decodeString.emapTry { str =>
    Try(Instant.parse(str))
  }

  /**
    * This ADT deserializer could be improved (but I'm not still so good with generic derivation for ADT's on Circe)
    */
  implicit val jsonInputDecoder: Decoder[JsonInput] = new Decoder[JsonInput] {

    def apply(cursor: HCursor): Result[JsonInput] =
      if (cursor.downField(ACCOUNT_FIELD).succeeded) decodeCreateAccount(cursor.downField(ACCOUNT_FIELD))
      else decodeAuthorizeTransaction(cursor.downField(TRANSACTION_FIELD))

    private def decodeCreateAccount(cursor: ACursor): Result[JsonInput] =
      for {
        activeCard     <- cursor.downField(ACTIVE_CARD_FIELD).as[Boolean]
        availableLimit <- cursor.downField(AVAILABLE_LIMIT_FIELD).as[Int]
      } yield CreateAccountInput(activeCard, availableLimit)

    private def decodeAuthorizeTransaction(c: ACursor): Result[JsonInput] =
      for {
        merchant <- c.downField(MERCHANT).as[String]
        amount   <- c.downField(AMOUNT).as[Int]
        time     <- c.downField(TIME).as[Instant]
      } yield AuthorizeTransactionInput(merchant, amount, time)
  }
}

object JsonFields {
  val ACCOUNT_FIELD         = "account"
  val TRANSACTION_FIELD     = "transaction"
  val ACTIVE_CARD_FIELD     = "active-card"
  val AVAILABLE_LIMIT_FIELD = "available-limit"
  val MERCHANT              = "merchant"
  val AMOUNT                = "amount"
  val TIME                  = "time"
}
