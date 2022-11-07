package esm.dnd.DnDDAM2EnriqueSergioMangel.repositorio;

import java.util.UUID;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import esm.dnd.DnDDAM2EnriqueSergioMangel.modelo.FichaPersonaje;

@Repository
public interface FichaPersonajeRepository extends MongoRepository<FichaPersonaje, UUID> {

	
}
