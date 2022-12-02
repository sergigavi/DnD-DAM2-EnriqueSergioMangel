package esm.dnd.DnDDAM2EnriqueSergioMangel.repositorio;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import esm.dnd.DnDDAM2EnriqueSergioMangel.modelo.Npc;

public interface NpcRepository extends MongoRepository<Npc,ObjectId> {
    
}
