package esm.dnd.DnDDAM2EnriqueSergioMangel.repositorio;

import java.util.List;
import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import esm.dnd.DnDDAM2EnriqueSergioMangel.modelo.FichaPersonaje;

@Repository
public interface FichaPersonajeRepository extends MongoRepository<FichaPersonaje, ObjectId> {

    @Query("{ 'inventario.idEquipo': ?0 } ")
    public Optional<List<FichaPersonaje>> findByEquipamientoId(ObjectId equipamientoId);


    //TODO: query para la edicion de la ficha

    // @Update(value = "",update = "")
    // public Boolean updateFichaRemovedEquipamiento();
}
