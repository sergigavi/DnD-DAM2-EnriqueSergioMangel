package esm.dnd.DnDDAM2EnriqueSergioMangel.servicio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import esm.dnd.DnDDAM2EnriqueSergioMangel.modelo.Equipamiento;
import esm.dnd.DnDDAM2EnriqueSergioMangel.repositorio.EquipamientoRepository;

@Service
public class EquipamientoServicio implements IEquipamientoServicio{
    
    @Autowired EquipamientoRepository equipamientoDAO;


    @Override
	public boolean eliminarEquipamiento(String idEquipo) {
		boolean exito=false;
		
		if(equipamientoDAO.existsById(idEquipo)) {
			equipamientoDAO.deleteById(idEquipo);
			exito=true;
		}
			
		return exito;
	}

    @Override
	public boolean existeEquipamiento(String idEquipo) {
		return equipamientoDAO.existsById(idEquipo);
	}

	@Override
	public boolean insertarEquipamiento(Equipamiento a) {
		boolean exito=false;
		
		if(!equipamientoDAO.existsById(a.getIdEquipo())) {
			equipamientoDAO.save(a);
			exito=true;
		}
		
		return exito;
	}
}
