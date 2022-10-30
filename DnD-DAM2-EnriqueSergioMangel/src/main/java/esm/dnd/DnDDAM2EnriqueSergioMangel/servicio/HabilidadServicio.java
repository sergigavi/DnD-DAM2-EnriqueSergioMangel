package esm.dnd.DnDDAM2EnriqueSergioMangel.servicio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import esm.dnd.DnDDAM2EnriqueSergioMangel.modelo.Habilidad;
import esm.dnd.DnDDAM2EnriqueSergioMangel.repositorio.HabilidadRepository;

public class HabilidadServicio implements IHabilidadServicio{
    
    @Autowired HabilidadRepository habilidadDAO;

    @Override
	public boolean insertarHabilidad(Habilidad a) {
		boolean exito=false;
		
		if(!habilidadDAO.existsById(a.getIdHabilidad())) {
			habilidadDAO.save(a);
			exito=true;
		}
		
		return exito;
	}

    @Override
	public boolean eliminarHabilidad(String idHabilidad) {
		boolean exito=false;
		
		if(habilidadDAO.existsById(idHabilidad)) {
			habilidadDAO.deleteById(idHabilidad);
			exito=true;
		}
			
		return exito;
	}

    @Override
	public boolean existeHabilidad(String idHabilidad) {
		return habilidadDAO.existsById(idHabilidad);
	}
}
