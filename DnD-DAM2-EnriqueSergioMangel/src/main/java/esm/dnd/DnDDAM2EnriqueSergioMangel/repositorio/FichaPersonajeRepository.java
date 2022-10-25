package esm.dnd.DnDDAM2EnriqueSergioMangel.repositorio;

import org.springframework.data.mongodb.repository.MongoRepository;

import esm.dnd.DnDDAM2EnriqueSergioMangel.modelo.FichaPersonaje;

public interface FichaPersonajeRepository extends MongoRepository<FichaPersonaje, String> {

	
}
