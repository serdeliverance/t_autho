package com.challenge.adapter.in.stdin

import cats.effect.IO
import com.challenge.adapter.command.CommandHandler
import com.challenge.adapter.json.JsonInput
import com.challenge.adapter.json.JsonInputOps.JsonInputDomainCommandConverter
import com.challenge.adapter.json.JsonParsing.commandDecoder
import fs2.io.stdin
import fs2.text
import io.circe.parser.decode
import io.circe.syntax._

class StdinReader(commandHandler: CommandHandler) {

  def read() =
    stdin[IO](512)
      .through(text.utf8.decode)
      .through(text.lines)
      .map(line => decode[JsonInput](line))
      .collect {
        case Right(json) => json
      }
      .map(_.toDomainCommand)
      .map(command => commandHandler.handle(command))
      .map(operationResult => operationResult.asJson)

}
