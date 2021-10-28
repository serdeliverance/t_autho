package com.challenge.adapter.json

import cats.syntax.functor._
import com.challenge.adapter.json.input.{AuthorizeTransactionJson, CreateAccountJson}
import io.circe.Decoder
import io.circe.generic.auto._

object JsonParsing extends CirceImplicits {
  implicit val commandDecoder: Decoder[JsonInput] =
    List[Decoder[JsonInput]](
      Decoder[AuthorizeTransactionJson].widen,
      Decoder[CreateAccountJson].widen
    ).reduceLeft(_ or _)
}
