package esm.dnd.DnDDAM2EnriqueSergioMangel.repositorio;



import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import esm.dnd.DnDDAM2EnriqueSergioMangel.modelo.Usuario;

@Repository
public interface UsuarioRepository extends MongoRepository<Usuario,String>{

	boolean existsByEmail(String email);

	Optional<Usuario> findByEmail(String email);

	Optional<Usuario> findByIdUserString(String id);
    
}
