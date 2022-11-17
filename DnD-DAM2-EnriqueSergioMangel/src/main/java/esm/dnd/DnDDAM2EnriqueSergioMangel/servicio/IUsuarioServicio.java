package esm.dnd.DnDDAM2EnriqueSergioMangel.servicio;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import esm.dnd.DnDDAM2EnriqueSergioMangel.modelo.Usuario;

public interface IUsuarioServicio {
    
    public boolean insertarUsuario(Usuario a);
    
	public boolean eliminarUsuario(UUID idUser);
	
	public boolean existeUsuario(UUID idUser);
	
    public boolean cambiarContrasenia(UUID idUser, String contrasenia);
    
    public boolean addAllUsuarios(List<Usuario> usuarios);
    
    public List<Usuario> findAllUsuarios();
    
    public Optional<Usuario> findUsuarioById(UUID idUsuario);

	public Iterable<Usuario> eliminarTodos();
}
