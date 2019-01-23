# SpringRepository
This Repository contains the example services that I have tried to learn basics of the Spring boot and Spring cloud tools functionalities.

## Installation of RabbitMQ (in Ubuntu Linux):

> Follow the installation steps as mentioned in the [RabbitMQ download](https://www.rabbitmq.com/install-debian.html) page.

## Inorder to run  Zipkin tracer with RabbitMQ.

1. Start the RabbitMQ server in the backend. When using linux `sudo service rabbitmq-server start`
2. Add env variable `export RABBIT_URI=amqp://localhost`
3. Start the Zipkin Server `java -jar zipkin.jar`

  Note : To learn basics of Spring cloud I have replicated Basic Currency Conversion Service Scenario

## Order of Startup and Execution of Services:

1. spring-cloud-config-server.
2. netflix-eureka-naming-server.(This service demonstrates the service discovery)
3. RabbitMQ
4. zipkin server.
5. currency-conversion-service. (This service has the feign client, uses ribbon to loadbalance between n instances of currency-exchange-service)
6. currency-exchange-service.(This service demonstrates the JPA connectivity, Distributed tracing using sleuth and passes the logs as message queue to the zipkin tracer)
7. limits-service. (This service demonstrates the Config client, Spring cloud bus that communicates using amqp and Spring cloud hystrix)
8. netflix-zuul-api-gateway-server (This service demonstrates the API gateway for the rest of the services and inherits a basic logging filter)
