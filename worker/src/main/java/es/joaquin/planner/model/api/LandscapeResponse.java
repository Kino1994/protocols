package es.joaquin.planner.model.api;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class LandscapeResponse {
	
	private String city;
	
	private String landscape;	

}
