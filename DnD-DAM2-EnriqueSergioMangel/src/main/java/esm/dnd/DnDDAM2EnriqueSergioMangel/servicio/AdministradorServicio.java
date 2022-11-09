package esm.dnd.DnDDAM2EnriqueSergioMangel.servicio;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import esm.dnd.DnDDAM2EnriqueSergioMangel.modelo.Administrador;
import esm.dnd.DnDDAM2EnriqueSergioMangel.repositorio.AdministradorRepository;

@Service
public class AdministradorServicio implements IAdministradorServicio{
    
    @Autowired AdministradorRepository administradorDAO;

    @Override
	public boolean insertarAdministrador(Administrador a) {
		boolean exito=false;
		
		if(!administradorDAO.existsById(a.getIdAdmin())) {
			administradorDAO.save(a);
			exito=true;
		}
		
		return exito;
	}

    @Override
	public boolean eliminarAdministrador(UUID idAdmin) {
		boolean exito=false;
		
		if(administradorDAO.existsById(idAdmin)) {
			administradorDAO.deleteById(idAdmin);
			exito=true;
		}
			
		return exito;
	}

    @Override
	public boolean existeAdministrador(UUID idAdmin) {
		return administradorDAO.existsById(idAdmin);
	}
}
