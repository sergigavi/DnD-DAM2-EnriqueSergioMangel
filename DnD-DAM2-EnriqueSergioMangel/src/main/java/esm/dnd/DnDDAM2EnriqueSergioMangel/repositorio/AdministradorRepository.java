package esm.dnd.DnDDAM2EnriqueSergioMangel.repositorio;


import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import esm.dnd.DnDDAM2EnriqueSergioMangel.modelo.Administrador;

@Repository
public interface AdministradorRepository extends MongoRepository<Administrador,String>{
    
    boolean existsByEmail(String email);

    Optional<Administrador> findByEmail(String email);
}
