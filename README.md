# Authorizer

## Stack

* `Scala`
* `Cats Effect` for effect management
* `Circe` for json parsing
* `Fs2` for stream processing
* `Mockito` for testing

## Design and architecture

I took the `Ports and Adapters` design pattern approach. Also, I tried to keep the app as minimalistic as possible using just the components that were really needed (such as `Circe`, for json parsing, and `Cats Effect`, for effect management).

The `domain` layer takes some ideas from `Clean Architecture` (for example: the `usecase` package, for expressing flows that takes different components together to perform business rules). Also, the domain itself tries to not be anemic, so components such as `Account`, `Transaction` and `AccountBalance` not express stores data but also exposes methods and functionalities.
It is important to mention the approach for dealing with `Validation` of the different business rules that constraints the operations offered by the app.
For this purpose, a generic `Validation` mixin was created. Its method `validate` receives a generic transaction to validate and an implicit `AccountProvider` (which is just a fancy alias for a function `() => Option[Account]`). This provider function is very useful because of its lazyness, which allows it to compose different validations and create composed validations (aka `ValidationAggregator`) at compiled time.
Another interesting thing about `Validation` is that it implements `Semigroup`, which is a functional programming pattern that allows element to the same time to be combined. This was used to fold the different validations and accumulate all the validation results that a `ValidationAggregator` computes in one sake.

The `adapter` layer takes its input from stdin through and Fs2 stream. The output side was implemented with an inmemory repository layer which is backed by a mutable variable on memory (just to keep it simple and not using a library or complicated wrappers around `IO monad`).

The `application` layer acts as a middleware which exposes ports that allows the communication between the `adapter` and `domain` layers.

For testing, I've just used Unit Test to validate the domain model and some layers. Sorry for the lack of integration tests.

## Requisites

* Docker

## Instructions

Build the image:

```
./build.sh
```

To run the app:

```
./run.sh < operations.txt
```

## Instructions for SBT users

If you want to compile, test and run the app with SBT there's no problem. You can use standard commands:

- for running tests:

```
sbt test
```

- for compiling the app:

```
sbt compile
```

- run the app:

```
sbt run
```