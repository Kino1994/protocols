package es.joaquin.topo.service;

import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.joaquin.topo.entities.Topo;
import es.joaquin.topo.model.api.Landscape;
import es.joaquin.topo.repository.TopoRepository;
import reactor.core.publisher.Flux;

@Service
public class TopoService {
	
	@Autowired
	private TopoRepository topoRepository;
	
	@PostConstruct
	private void initTopographies() {
		topoRepository.deleteAll().block();
		Flux<Topo> topos = Flux.just(
			new Topo("Madrid", Landscape.FLAT.getValue()),
			new Topo("Barcelona", Landscape.FLAT.getValue()),
	        new Topo("Oviedo", Landscape.MOUNTAIN.getValue()),
			new Topo("Soria", Landscape.MOUNTAIN.getValue()),
			new Topo("Covadonga", Landscape.MOUNTAIN.getValue())
		);

		topos.flatMap(this.topoRepository::save)
	    	.blockLast();
	}
	
	public Flux<Topo> getTopographies(String id){
		return topoRepository.findAllById(List.of(id));
	}
	
}
