package esm.dnd.DnDDAM2EnriqueSergioMangel.servicio;

import esm.dnd.DnDDAM2EnriqueSergioMangel.modelo.Usuario;

public interface IUsuarioServicio {
    
    public boolean insertarUsuario(Usuario a);
	public boolean eliminarUsuario(String idUser);
	public boolean existeUsuario(String idUser);
    public boolean cambiarContrasenia(String idUser, String contrasenia);
}
