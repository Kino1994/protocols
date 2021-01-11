# TopoService

This API return the landscape of a stored city in a MongoDB

## Requirements

Building the API requires:

1. Java 11

2. Maven

## Run

To run the API

```shell
docker run --rm -p 27017:27017 -d mongo:4.4-bionic
mvn compile
mvn spring-boot:run
```
