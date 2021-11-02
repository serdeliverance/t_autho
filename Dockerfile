FROM hseeberger/scala-sbt:11.0.13_1.5.5_2.13.6

COPY . /app
WORKDIR /app

RUN sbt assembly && cp target/scala-2.13/authorizer .
CMD ["java", "-jar", "authorizer"]