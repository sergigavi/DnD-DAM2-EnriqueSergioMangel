package esm.dnd.DnDDAM2EnriqueSergioMangel.servicio;

import java.util.List;
import java.util.Optional;

import org.bson.types.ObjectId;

import esm.dnd.DnDDAM2EnriqueSergioMangel.modelo.Usuario;

public interface IUsuarioServicio {
    
    public boolean insertarUsuario(Usuario a);
    
	public boolean eliminarUsuario(String idUser);
	
	public boolean existeUsuario(String idUser);
	
    public boolean cambiarContrasenia(String idUser, String contrasenia);
    
    public boolean addAllUsuarios(List<Usuario> usuarios);
    
    public List<Usuario> findAllUsuarios();
    
    public Optional<Usuario> findUsuarioById(String idUsuario);

	public Iterable<Usuario> eliminarTodos();

	public boolean existsByEmail(String email);

	public Optional<Usuario> findByEmail(String email);

    public Optional<Usuario> editarUsuario(Usuario usuario);

    public List<Usuario> getAll();

    public long getCantidad();
    
	public Optional<Usuario> findUsuarioByIdString(String id);
}
