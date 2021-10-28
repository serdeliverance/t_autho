package com.challenge.adapter.json

import cats.syntax.functor._
import com.challenge.adapter.json.input.{AuthorizeTransactionJson, CreateAccountJson}
import com.challenge.domain.entity.{AccountBalance, OperationResult}
import io.circe.{Decoder, Encoder}
import io.circe.generic.auto._
import io.circe.generic.extras.semiauto.deriveConfiguredEncoder

object JsonParsing extends CirceImplicits {

  implicit val operationResultEncoder: Encoder[OperationResult] = deriveConfiguredEncoder

  implicit val accountBalanceEncoder: Encoder[AccountBalance] = deriveConfiguredEncoder

  implicit val commandDecoder: Decoder[JsonInput] =
    List[Decoder[JsonInput]](
      Decoder[AuthorizeTransactionJson].widen,
      Decoder[CreateAccountJson].widen
    ).reduceLeft(_ or _)
}
