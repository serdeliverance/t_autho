package com.challenge

import cats.effect.{ExitCode, IO, IOApp}

object Authorizer extends IOApp {
  override def run(args: List[String]): IO[ExitCode] =
    IO(ExitCode.Success)
}
