package esm.dnd.DnDDAM2EnriqueSergioMangel.servicio;

import java.util.List;
import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import esm.dnd.DnDDAM2EnriqueSergioMangel.modelo.Equipamiento;
import esm.dnd.DnDDAM2EnriqueSergioMangel.modelo.FichaPersonaje;
import esm.dnd.DnDDAM2EnriqueSergioMangel.repositorio.EquipamientoRepository;
import esm.dnd.DnDDAM2EnriqueSergioMangel.repositorio.FichaPersonajeRepository;

@Service
public class EquipamientoServicio implements IEquipamientoServicio{
    
    @Autowired EquipamientoRepository equipamientoDAO;
	@Autowired FichaPersonajeRepository fichaPersonajeDAO;

    @Override
	public boolean eliminarEquipamiento(ObjectId idEquipo) {
		boolean exito=false;
		
		if(equipamientoDAO.existsById(idEquipo)) {
			Optional<List<FichaPersonaje>> personajes= fichaPersonajeDAO.findByEquipamientoId(idEquipo);
			if(personajes.isPresent()){
				personajes.get().stream()
				.map(p->p.getInventario())
				.flatMap(i->i.stream())
				.forEach(eq->{
					eq.deleteById(idEquipo);
					
				});
			}
		}
			
		return exito;
	}

    @Override
	public boolean existeEquipamiento(ObjectId idEquipo) {
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

	@Override
	public List<Equipamiento> getAll() {

		return equipamientoDAO.findAll();
	}

	@Override
	public boolean eliminarEquipamientoTemp(ObjectId idEquipo) {
		
		if(equipamientoDAO.existsById(idEquipo)){
			equipamientoDAO.deleteById(idEquipo);
		return true;
		}else{
			return false;
		}
	}
}
