package es.joaquin.planner.clients;

import java.util.concurrent.CompletableFuture;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import es.joaquin.planner.model.api.LandscapeResponse;

@Component
public class TopoServiceClient {
	
	@Autowired
	private RestTemplate restTemplate;
	
	@Value("${toposervice.url}")
    private String url;
	
	public CompletableFuture<String> getLandscape(String city) {
			
		LandscapeResponse response = restTemplate.exchange(url, HttpMethod.GET, null, LandscapeResponse.class).getBody();
		
		return CompletableFuture.completedFuture(response.getLandscape());
	}

}
