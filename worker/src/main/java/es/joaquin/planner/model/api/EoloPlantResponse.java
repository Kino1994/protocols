package es.joaquin.planner.model.api;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class EoloPlantResponse {
	
	private Integer id;
	
	private String city;
	
	private Integer progress;
	
	private boolean completed;
	
	private String planning;
	

	public void setCompleted(boolean completed) {
		this.completed = completed;
	}
	
	public boolean isCompleted() {
		return this.getProgress().equals(100);
	}
	
}
