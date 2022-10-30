package esm.dnd.DnDDAM2EnriqueSergioMangel.servicio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import esm.dnd.DnDDAM2EnriqueSergioMangel.modelo.FichaPersonaje;
import esm.dnd.DnDDAM2EnriqueSergioMangel.repositorio.FichaPersonajeRepository;

public class FichaPersonajeServicio implements IFichaPersonajeServicio{
    
    @Autowired FichaPersonajeRepository fichaPersonajeDAO;

    @Override
	public boolean insertarFichaPersonaje(FichaPersonaje a) {
		boolean exito=false;
		
		if(!fichaPersonajeDAO.existsById(a.getIdFichaPersonaje())) {
			fichaPersonajeDAO.save(a);
			exito=true;
		}
		
		return exito;
	}

    @Override
	public boolean eliminarFichaPersonaje(String idFichaPersonaje) {
		boolean exito=false;
		
		if(fichaPersonajeDAO.existsById(idFichaPersonaje)) {
			fichaPersonajeDAO.deleteById(idFichaPersonaje);
			exito=true;
		}
			
		return exito;
	}

    @Override
	public boolean existeFichaPersonaje(String idFichaPersonaje) {
		return fichaPersonajeDAO.existsById(idFichaPersonaje);
	}
}
