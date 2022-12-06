package esm.dnd.DnDDAM2EnriqueSergioMangel.servicio;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.bson.types.ObjectId;

import esm.dnd.DnDDAM2EnriqueSergioMangel.modelo.Usuario;

public interface IUsuarioServicio {
    
    public boolean insertarUsuario(Usuario a);
    
	public boolean eliminarUsuario(ObjectId idUser);
	
	public boolean existeUsuario(ObjectId idUser);
	
    public boolean cambiarContrasenia(ObjectId idUser, String contrasenia);
    
    public boolean addAllUsuarios(List<Usuario> usuarios);
    
    public List<Usuario> findAllUsuarios();
    
    public Optional<Usuario> findUsuarioById(ObjectId idUsuario);

	public Iterable<Usuario> eliminarTodos();

	public boolean existsByNickname(String nickname);

	public Optional<Usuario> findUsuarioByNickname(String nickname);

    public boolean actualizarUsuario(Usuario a);
}
