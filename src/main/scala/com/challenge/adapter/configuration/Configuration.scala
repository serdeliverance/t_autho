package com.challenge.adapter.configuration

import cats.effect.IO
import pureconfig.ConfigSource
import pureconfig.generic.auto._

object Configuration {

  case class AuthorizerConfiguration(
    highFrequencyInterval: Int,
    highFrequencyMaxAllowed: Int,
    doubledTransactionInterval: Int
  )

  def loadConfiguration: IO[AuthorizerConfiguration] =
    ConfigSource.default.load[AuthorizerConfiguration] match {
      case Right(conf) => IO(conf)
      case Left(err)   => IO.raiseError(new IllegalArgumentException(s"Error loading configuration. $err"))
    }
}
