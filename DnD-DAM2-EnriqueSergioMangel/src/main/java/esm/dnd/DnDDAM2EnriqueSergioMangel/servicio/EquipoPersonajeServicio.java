package esm.dnd.DnDDAM2EnriqueSergioMangel.servicio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import esm.dnd.DnDDAM2EnriqueSergioMangel.modelo.Equipo_Personaje;
import esm.dnd.DnDDAM2EnriqueSergioMangel.repositorio.EquipoPersonajeRepository;

@Service
public class EquipoPersonajeServicio implements IEquipoPersonajeServicio{
    
    @Autowired EquipoPersonajeRepository equipoPersonajeDAO;

    @Override
	public boolean insertarEquipoPersonaje(Equipo_Personaje a) {
		boolean exito=false;
		
		if(!equipoPersonajeDAO.existsById(a.getIdEquipo())) {
			equipoPersonajeDAO.save(a);
			exito=true;
		}
		
		return exito;
	}

    @Override
	public boolean eliminarEquipoPersonaje(String idEquipo) {
		boolean exito=false;
		
		if(equipoPersonajeDAO.existsById(idEquipo)) {
			equipoPersonajeDAO.deleteById(idEquipo);
			exito=true;
		}
			
		return exito;
	}

    @Override
	public boolean existeEquipoPersonaje(String idEquipo) {
		return equipoPersonajeDAO.existsById(idEquipo);
	}
}
