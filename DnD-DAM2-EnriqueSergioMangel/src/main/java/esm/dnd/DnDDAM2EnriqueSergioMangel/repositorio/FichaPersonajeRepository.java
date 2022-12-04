package esm.dnd.DnDDAM2EnriqueSergioMangel.repositorio;


import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import esm.dnd.DnDDAM2EnriqueSergioMangel.modelo.FichaPersonaje;

@Repository
public interface FichaPersonajeRepository extends MongoRepository<FichaPersonaje, ObjectId> {


    //TODO: query para la edicion de la ficha

    // @Update(value = "",update = "")
    // public Boolean updateFichaRemovedEquipamiento();
}
