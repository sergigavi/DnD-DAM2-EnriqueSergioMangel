package esm.dnd.DnDDAM2EnriqueSergioMangel.repositorio;


import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import esm.dnd.DnDDAM2EnriqueSergioMangel.modelo.Administrador;

@Repository
public interface AdministradorRepository extends MongoRepository<Administrador,ObjectId>{
    
}
