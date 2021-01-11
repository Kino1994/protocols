# Protocols

## Run

```shell
docker run --rm -p 27017:27017 -d mongo:4.4-bionic
docker run --rm -p 5672:5672 -p 15672:15672 rabbitmq:3-management
docker run -p 3306:3306 --name mysql -e MYSQL_ROOT_PASSWORD=pass -e MYSQL_DATABASE=eoloplants -e -d mysql:latest
node install.js
node excec.js
```
