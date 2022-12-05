package esm.dnd.DnDDAM2EnriqueSergioMangel.repositorio;

import org.springframework.data.mongodb.repository.MongoRepository;

import esm.dnd.DnDDAM2EnriqueSergioMangel.modelo.Partida;

public interface PartidaRepository extends MongoRepository<Partida,String> {
    
}
