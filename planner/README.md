# Planner

This planner return the progres of TopoService and WeatherService

## Requirements

Building the API requires:

1. Java 11

2. Maven

## Run

To run the API

```shell
docker run --rm -p 5672:5672 -p 15672:15672 rabbitmq:3-management
mvn compile
mvn spring-boot:run
```
