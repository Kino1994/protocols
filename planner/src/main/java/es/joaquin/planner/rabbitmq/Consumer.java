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

		eoloplantResponse.setProgress(0);
		eoloplantResponse.setPlanning(null);
		eoloplantResponse.setCompleted(false);
		producer.received(eoloplantResponse);

		CompletableFuture<String> weatherFuture = weatherServiceClient.getWeather(eoloplantResponse.getCity());
		CompletableFuture<String> landscapeFuture = topoServiceClient.getLandscape(eoloplantResponse.getCity());
		CompletableFuture<Void> completedFutures = CompletableFuture.allOf(weatherFuture, landscapeFuture);

		eoloplantResponse.setProgress(25);
		eoloplantResponse.setPlanning(null);
		eoloplantResponse.setCompleted(false);
		producer.received(eoloplantResponse);

		weatherFuture.thenRun(() -> {
			eoloplantResponse.setPlanning(eoloplantResponse.getCity() + weatherFuture.join() + SEPARATOR);
			if (eoloplantResponse.getCity().matches("^[A-Ma-m].*")) {
				eoloplantResponse.setPlanning(eoloplantResponse.getPlanning().toLowerCase());
			}
			else {
				eoloplantResponse.setPlanning(eoloplantResponse.getPlanning().toUpperCase());
			}
			eoloplantResponse.setProgress(eoloplantResponse.getProgress() + 25);
			eoloplantResponse.setCompleted(false);
			producer.received(eoloplantResponse);
		});

		landscapeFuture.thenRun(() -> {
			eoloplantResponse.setPlanning(eoloplantResponse.getCity() + landscapeFuture.join() + SEPARATOR);
			if (eoloplantResponse.getCity().matches("^[A-Ma-m].*")) {
				eoloplantResponse.setPlanning(eoloplantResponse.getPlanning().toLowerCase());
			}
			else {
				eoloplantResponse.setPlanning(eoloplantResponse.getPlanning().toUpperCase());
			}
			eoloplantResponse.setProgress(eoloplantResponse.getProgress() + 25);
			eoloplantResponse.setCompleted(false);
			producer.received(eoloplantResponse);
		});

		completedFutures.thenRun(() -> {
			eoloplantResponse.setPlanning(eoloplantResponse.getPlanning().substring(0, eoloplantResponse.getPlanning().length() -1));
			eoloplantResponse.setProgress(100);
			eoloplantResponse.setCompleted(true);
			producer.received(eoloplantResponse);
		});
	}
}