const grpc = require('grpc');
const WeatherService = require('./interface');
const WeatherServiceImpl = require('./weatherService');

const server = new grpc.Server();

server.addService(WeatherService.service, WeatherServiceImpl);

server.bind('localhost:9090', grpc.ServerCredentials.createInsecure());

console.log('gRPC server running at localhost:9090');

server.start();