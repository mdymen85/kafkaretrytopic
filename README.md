# Kafka Retry Topic

This project aims to implement a similar code as the 4th error handling patterns for kafka as the [confluent site](https://www.confluent.io/blog/error-handling-patterns-in-kafka/).

# How it works

There are three main projects in this implementation:
- Consumer
- Producer
- Retry consumer

The first one, the **consumer**, will consume a message from the **producer**, and will throw an exception -just for testig porpuses-. And, because of this exception, in the catch block, the code will add a message in a pending table to be sent to a retry topic. After this, the consumer will consume the same message but, in this case, from the **retry-topic**, and will redirect it to a specific retry topic where **retry consumer**, other instance of **consumer** will consume that message.

### What happend if i want to maintain the order the messages with the same key?

In this case, in the **control_key** table, we save the key reference of the messages, and a value starting with 1. That represents the quantity of messages that are being redirected to the **retry-topic**. Every time that a new message with the same key arrives to the **consumer** will check if needs to redirect to the **retry-topic** and, in such case, will add 1 to the **control_key** table. 

After a messsage, with a specific key, is consumed from the **retry-topic**, will substract 1 from the **control_key** table. After all messages from the same key had been consumed in the **retry-consumer**, the row from the control_key table with that key will be deleted. So, in this case, the **consumer** will continue processing the message without the need of redirect the message to the retry-topic.

![image](https://user-images.githubusercontent.com/8418011/178128645-d38843b9-76d3-4e5b-8023-15b27163a29b.png)

In order to use the same application as **consumer** and **retry-consumer** some configuration must be implemented. So, in cases of a consumer some classes and methods will be loaded, and in case of a retry-consumer, some other classes won't be loaded.
