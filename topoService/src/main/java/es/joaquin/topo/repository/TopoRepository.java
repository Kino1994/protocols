package es.joaquin.topo.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

import es.joaquin.topo.entities.Topo;

@Repository
public interface TopoRepository extends ReactiveMongoRepository<Topo, String> {
		
}
