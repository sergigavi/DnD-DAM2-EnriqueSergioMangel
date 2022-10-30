package esm.dnd.DnDDAM2EnriqueSergioMangel.repositorio;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import esm.dnd.DnDDAM2EnriqueSergioMangel.modelo.Equipo_Personaje;

@Repository
public interface EquipoPersonajeRepository extends MongoRepository<Equipo_Personaje,String>{
    
}
