package esm.dnd.DnDDAM2EnriqueSergioMangel.servicio;

import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import esm.dnd.DnDDAM2EnriqueSergioMangel.modelo.Usuario;
import esm.dnd.DnDDAM2EnriqueSergioMangel.repositorio.UsuarioRepository;

@Service
public class UsuarioServicio implements IUsuarioServicio{
    
    @Autowired UsuarioRepository usuarioDAO;

    @Override
	public boolean insertarUsuario(Usuario a) {
		boolean exito=false;
		
		if(!usuarioDAO.existsById(a.getIdUser())) {
			usuarioDAO.save(a);
			exito=true;
		}
		
		return exito;
	}

    @Override
	public boolean eliminarUsuario(UUID idUser) {
		boolean exito=false;
		
		if(usuarioDAO.existsById(idUser)) {
			usuarioDAO.deleteById(idUser);
			exito=true;
		}
			
		return exito;
	}

    @Override
	public boolean existeUsuario(UUID idUser) {
		return usuarioDAO.existsById(idUser);
	}

    @Override
    public boolean cambiarContrasenia(UUID idUser, String contrasenia){
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

	@Override
	public boolean addAllUsuarios(Iterable<Usuario> usuarios) {
		boolean exito = false;
        
        try {
            
            usuarioDAO.saveAll(usuarios);
            exito = true;
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return exito;
	}

	@Override
	public Iterable<Usuario> findAllUsuarios() {
		return usuarioDAO.findAll();
	}

	@Override
	public Optional<Usuario> findUsuarioById(UUID idUsuario) {
		return usuarioDAO.findById(idUsuario);
	}
}
