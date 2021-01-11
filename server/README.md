# Server

## Requirements

1. Node.js

## Run

To run the server

```shell
docker run -p 3306:3306 --name mysql -e MYSQL_ROOT_PASSWORD=pass -e MYSQL_DATABASE=eoloplants -e -d mysql:latest
nmp install
node server.js
```
