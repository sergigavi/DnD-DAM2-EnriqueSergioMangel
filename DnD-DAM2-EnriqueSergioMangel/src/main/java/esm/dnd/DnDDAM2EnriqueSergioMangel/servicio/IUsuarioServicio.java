package esm.dnd.DnDDAM2EnriqueSergioMangel.servicio;

import java.util.Optional;

import esm.dnd.DnDDAM2EnriqueSergioMangel.modelo.Usuario;

public interface IUsuarioServicio {
    
    public boolean insertarUsuario(Usuario a);
    
	public boolean eliminarUsuario(String idUser);
	
	public boolean existeUsuario(String idUser);
	
    public boolean cambiarContrasenia(String idUser, String contrasenia);
    
    public boolean addAllUsuarios(Iterable<Usuario> usuarios);
    
    public Iterable<Usuario> findAllUsuarios();
    
    public Optional<Usuario> findUsuarioById(String idUsuario);
}
