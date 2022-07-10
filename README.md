# Kafka Retry Topic

This project aims to implement a similar code as the 4th error handling patterns for kafka as the [confluent site](https://www.confluent.io/blog/error-handling-patterns-in-kafka/).

# Introduction

There are three main projects in this implementation:
- Consumer
- Producer
- Retry consumer

The first one, the *consumer* will consume a message from the *produce*, and will throw an exception -just for testig porpuses-. Because of this exception, in the catch block, the code will add a message in a pending table to be sent to a retry topic. Because of the exception, the consumer will consume the same message but, in this case, from the retry-topic, and will redirect it to a specific retry topic where other instance will consume that message.

