package com.challenge.adapter.in.stdin

import cats.effect.IO
import com.challenge.adapter.command.CommandHandler
import com.challenge.adapter.json.JsonInput.JsonInput
import com.challenge.adapter.json.JsonInputOps.JsonInputDomainCommandConverter
import com.challenge.adapter.json.JsonParsing._
import fs2.io.{stdin, stdout}
import fs2.{INothing, Stream, text}
import io.circe.parser.decode
import io.circe.syntax._

case class StdinReader(commandHandler: CommandHandler) {

  def read(): Stream[IO, INothing] =
    stdin[IO](512)
      .through(text.utf8.decode)
      .through(text.lines)
      .map(_.trim)
      .filter(_.nonEmpty)
      .map(line => decode[JsonInput](line))
      .collect {
        case Right(json) => json
      }
      .map(_.toDomainCommand)
      .map(command => commandHandler.handle(command))
      .map(operationResult => operationResult.asJson)
      .map(_.noSpaces)
      .map(line => line + System.lineSeparator())
      .through(text.utf8.encode)
      .through(stdout[IO])
}
