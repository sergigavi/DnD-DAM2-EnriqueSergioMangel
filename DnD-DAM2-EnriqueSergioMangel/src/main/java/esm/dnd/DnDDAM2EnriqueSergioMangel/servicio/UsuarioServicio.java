package esm.dnd.DnDDAM2EnriqueSergioMangel.servicio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import esm.dnd.DnDDAM2EnriqueSergioMangel.modelo.Usuario;
import esm.dnd.DnDDAM2EnriqueSergioMangel.repositorio.UsuarioRepository;

@Service
public class UsuarioServicio {
    
    @Autowired UsuarioRepository usuarioDAO;

	public boolean insertarUsuario(Usuario a) {
		boolean exito=false;
		
		if(!usuarioDAO.existsById(a.getIdUser())) {
			usuarioDAO.save(a);
			exito=true;
		}
		
		return exito;
	}

	public boolean eliminarUsuario(String idUser) {
		boolean exito=false;
		
		if(usuarioDAO.existsById(idUser)) {
			usuarioDAO.deleteById(idUser);
			exito=true;
		}
			
		return exito;
	}

	public boolean existeUsuario(String idUser) {
		return usuarioDAO.existsById(idUser);
	}

    public boolean cambiarContraseña(String idUser, String contrasenia){
        boolean exito=false;
        Usuario a;

        if(usuarioDAO.existsById(idUser)) {
            a=usuarioDAO.findById(idUser).get();
            Usuario user=Usuario.builder()
                .idUser(idUser)
                .nombre(a.getNombre())
                .apellidos(a.getApellidos())
                .contrasenia(contrasenia)
                .nickname(a.getNickname())
                .biografia(a.getBiografia())
                .email(a.getEmail())
                .fechaNacimiento(a.getFechaNacimiento())
                .urlImage(a.getUrlImage())
                .activo(a.isActivo())
                .pais(a.getPais())
                .build();

            usuarioDAO.save(user);
            exito=true;
        }
        
        return exito;
    }
}
