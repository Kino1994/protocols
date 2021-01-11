package es.joaquin.planner.rabbitmq;

import java.util.concurrent.CompletableFuture;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import es.joaquin.planner.clients.TopoServiceClient;
import es.joaquin.planner.clients.WeatherServiceClient;
import es.joaquin.planner.model.api.EoloPlantResponse;

@Component
public class Consumer {

	@Autowired
	private Producer producer;

	@Autowired
	private WeatherServiceClient weatherServiceClient;

	@Autowired
	private TopoServiceClient topoServiceClient;

	private static final String SEPARATOR = "-";

	@RabbitListener(queues = "eoloplantCreationRequests", ackMode = "AUTO")
	public void sendMessage(EoloPlantResponse eoloplantResponse) throws InterruptedException {

		String city;

		if (eoloplantResponse.getCity().matches("^[A-Ma-m].*")) {
			city = eoloplantResponse.getCity().toLowerCase();
		}

		else {
			city = eoloplantResponse.getCity().toUpperCase();
		}

		eoloplantResponse.setProgress(0);
		eoloplantResponse.setPlanning(city + SEPARATOR);
		producer.received(eoloplantResponse);

		CompletableFuture<String> weatherFuture = weatherServiceClient.getWeather(eoloplantResponse.getCity());
		CompletableFuture<String> landscapeFuture = topoServiceClient.getLandscape(eoloplantResponse.getCity());
		CompletableFuture<Void> completedFutures = CompletableFuture.allOf(weatherFuture, landscapeFuture);

		eoloplantResponse.setProgress(25);
		producer.received(eoloplantResponse);

		weatherFuture.thenRun(() -> {
			eoloplantResponse.setPlanning(eoloplantResponse.getCity() + weatherFuture.join() + SEPARATOR);
			eoloplantResponse.setProgress(eoloplantResponse.getProgress() + 25);
			producer.received(eoloplantResponse);
		});

		landscapeFuture.thenRun(() -> {
			eoloplantResponse.setPlanning(eoloplantResponse.getCity() + landscapeFuture.join() + SEPARATOR);
			eoloplantResponse.setProgress(eoloplantResponse.getProgress() + 25);
			producer.received(eoloplantResponse);
		});

		completedFutures.thenRun(() -> {
			eoloplantResponse.setProgress(100);
			producer.received(eoloplantResponse);
		});
	}
}