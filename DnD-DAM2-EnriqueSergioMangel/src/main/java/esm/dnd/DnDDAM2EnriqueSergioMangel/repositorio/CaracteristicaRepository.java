package esm.dnd.DnDDAM2EnriqueSergioMangel.repositorio;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import esm.dnd.DnDDAM2EnriqueSergioMangel.modelo.Caracteristica;

@Repository
public interface CaracteristicaRepository extends MongoRepository<Caracteristica,String>{
    
}
