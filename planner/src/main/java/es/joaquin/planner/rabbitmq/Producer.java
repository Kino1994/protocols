package es.joaquin.planner.rabbitmq;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import es.joaquin.planner.model.api.EoloPlantResponse;

@Component
public class Producer {

	@Autowired
	RabbitTemplate rabbitTemplate;

	public void received(EoloPlantResponse eoloplantResponse) {
		
		try {
			TimeUnit.MILLISECONDS.sleep(new Random().nextInt(2000) + 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		rabbitTemplate.convertAndSend("eoloplantResponseCreationProgressNotifications", eoloplantResponse);		
	}
}