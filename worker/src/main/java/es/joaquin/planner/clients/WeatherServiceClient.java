package es.joaquin.planner.clients;

import es.joaquin.grpc.GetWeatherRequest;
import es.joaquin.grpc.Weather;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.util.concurrent.CompletableFuture;

import static es.joaquin.grpc.WeatherServiceGrpc.WeatherServiceBlockingStub;


@Component
public class WeatherServiceClient {

    @GrpcClient("weatherServer")
    private WeatherServiceBlockingStub client;

    @Async
    public CompletableFuture<String> getWeather(String city) {

        GetWeatherRequest request = GetWeatherRequest.newBuilder()
                .setCity(city)
                .build();

        Weather response = this.client.getWeather(request);

        return CompletableFuture.completedFuture(response.getWeather());
    }
}