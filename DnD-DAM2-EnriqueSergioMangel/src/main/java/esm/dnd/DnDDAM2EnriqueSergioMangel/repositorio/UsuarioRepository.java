package esm.dnd.DnDDAM2EnriqueSergioMangel.repositorio;



import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import esm.dnd.DnDDAM2EnriqueSergioMangel.modelo.Usuario;

@Repository
public interface UsuarioRepository extends MongoRepository<Usuario,ObjectId>{

	boolean existsByNickname(String nickname);

	Optional<Usuario> findByNickname(String nickname);
    
}
