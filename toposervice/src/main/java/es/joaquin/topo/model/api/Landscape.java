package es.joaquin.topo.model.api;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum Landscape {
	
	FLAT("flat"),
	
	MOUNTAIN("mountain");
	
	private String value;
			
};	
