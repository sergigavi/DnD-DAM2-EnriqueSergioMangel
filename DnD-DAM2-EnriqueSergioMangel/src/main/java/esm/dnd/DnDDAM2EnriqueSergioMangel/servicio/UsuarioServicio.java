package esm.dnd.DnDDAM2EnriqueSergioMangel.servicio;

import java.util.List;
import java.util.Optional;

import org.bson.types.ObjectId;
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
		
		if(!usuarioDAO.existsById(a.getIdUserString())) {
			usuarioDAO.save(a);
			exito=true;
		}
		
		return exito;
	}

    @Override
	public boolean eliminarUsuario(String idUser) {
		boolean exito=false;
		
		if(usuarioDAO.existsById(idUser)) {
			usuarioDAO.deleteById(idUser);
			exito=true;
		}
			
		return exito;
	}

    @Override
	public boolean existeUsuario(String idUser) {
		return usuarioDAO.existsById(idUser);
	}

	@Override
	public List<Usuario> getAll() {
		return usuarioDAO.findAll();
	}

	@Override
	public Optional<Usuario> editarUsuario(Usuario usuario) {
		Optional<Usuario> us = Optional.empty();
		if(usuarioDAO.existsById(usuario.getIdUserString())){
			List<Usuario> usuarios=usuarioDAO.findAll();

			Usuario e = usuarioDAO.findById(usuario.getIdUserString()).get();
                e.setNombre(usuario.getNombre());
                e.setApellidos(e.getApellidos());
                e.setContrasenia(e.getContrasenia());
                e.setNickname(e.getNickname());
                e.setBiografia(e.getBiografia());
                e.setEmail(usuario.getEmail());
                e.setFechaNacimiento(e.getFechaNacimiento());
                e.setUrlImage(e.getUrlImage());
                e.setActivo(e.isActivo());
                e.setPais(e.getPais());
                
            usuarioDAO.save(e);
			//Esta linea es redundante, pero con ella sacamos el objeto acualizado de la base de datos
			//por si queremos hacer algo con el
			us=usuarioDAO.findById(e.getIdUserString());
			return us;
		}
		return us;
	}

    @Override
    public boolean cambiarContrasenia(String idUser, String contrasenia){
        boolean exito=false;
        Usuario a;

        if(usuarioDAO.existsById(idUser)) {
            a=usuarioDAO.findById(idUser).get();
            a.setContrasenia(contrasenia);

            usuarioDAO.save(a);
            exito=true;
        }
        
        return exito;
    }

	@Override
	public boolean addAllUsuarios(List<Usuario> usuarios) {
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
	public List<Usuario> findAllUsuarios() {
		return usuarioDAO.findAll();
	}

	@Override
	public Optional<Usuario> findUsuarioById(String idUsuario) {
		return usuarioDAO.findById(idUsuario);
	}

	@Override
	public Iterable<Usuario> eliminarTodos() {
		
		Iterable<Usuario> users =  usuarioDAO.findAll();

		usuarioDAO.deleteAll();
		
		return users;
		
	}

	@Override
	public boolean existsByEmail(String email) {
		return usuarioDAO.existsByEmail(email);
	}

	@Override
	public Optional<Usuario> findByEmail(String email) {
		return usuarioDAO.findByEmail(email);
	}

	@Override
	public Optional<Usuario> findUsuarioByIdString(String id) {
		return usuarioDAO.findByIdUserString(id);
	}
}
