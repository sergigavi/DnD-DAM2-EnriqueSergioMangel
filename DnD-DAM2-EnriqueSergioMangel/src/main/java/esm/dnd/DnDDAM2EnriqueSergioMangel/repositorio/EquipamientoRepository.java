package esm.dnd.DnDDAM2EnriqueSergioMangel.repositorio;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import esm.dnd.DnDDAM2EnriqueSergioMangel.modelo.Equipamiento;

@Repository
public interface EquipamientoRepository extends MongoRepository<Equipamiento,String>{
    
}
