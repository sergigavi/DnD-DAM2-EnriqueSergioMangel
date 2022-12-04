package esm.dnd.DnDDAM2EnriqueSergioMangel.servicio;

import java.util.List;
import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import esm.dnd.DnDDAM2EnriqueSergioMangel.modelo.Equipamiento;
import esm.dnd.DnDDAM2EnriqueSergioMangel.modelo.FichaPersonaje;
import esm.dnd.DnDDAM2EnriqueSergioMangel.modelo.Partida;
import esm.dnd.DnDDAM2EnriqueSergioMangel.repositorio.EquipamientoRepository;
import esm.dnd.DnDDAM2EnriqueSergioMangel.repositorio.FichaPersonajeRepository;
import esm.dnd.DnDDAM2EnriqueSergioMangel.repositorio.PartidaRepository;

@Service
public class EquipamientoServicio implements IEquipamientoServicio{
    
    @Autowired EquipamientoRepository equipamientoDAO;
	@Autowired FichaPersonajeRepository fichaPersonajeDAO;
	@Autowired PartidaRepository partidaDAO;

    @Override
	public boolean eliminarEquipamiento(ObjectId idEquipo) {
		boolean exito=false;
		
		if(equipamientoDAO.existsById(idEquipo)) {
			Equipamiento e=equipamientoDAO.findById(idEquipo).get();
			List<FichaPersonaje> personajes= fichaPersonajeDAO.findAll();
			List<Partida> partidas = partidaDAO.findAll();
			//Primero borramos los equipamientos de los inventarios que tengan el equipo mencionado
			//y despues borramos el objeto equipo de la base de datos
			if(!personajes.isEmpty()){
					personajes.stream()
					.map(p->p.getInventario())
					.forEach(i->i.remove(e));
				fichaPersonajeDAO.saveAll(personajes);
			}
			if(!partidas.isEmpty()){
				partidas.stream()
					.flatMap(p->p.getFichasPartida().stream())
					.map(f->f.getInventario())
					.forEach(i->i.remove(e));
				partidaDAO.saveAll(partidas);
			}
			equipamientoDAO.delete(e);
			exito=true;
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

	@Override
	public Optional<Equipamiento> editarEquipo(Equipamiento equipamiento) {
		Optional<Equipamiento> eq = Optional.empty();
		if(equipamientoDAO.existsById(equipamiento.getIdEquipo())){
			List<FichaPersonaje> personajes=fichaPersonajeDAO.findAll();
			if(!personajes.isEmpty()){
				personajes.stream()
					.flatMap(p->p.getInventario().stream()).forEach(e->{
						if(e.getIdEquipo().equals(equipamiento.getIdEquipo())){
							e=equipamiento;
						}
					});
				fichaPersonajeDAO.saveAll(personajes);
			}
			equipamientoDAO.save(equipamiento);
			//Esta linea es redundante, pero con ella sacamos el objeto acualizado de la base de datos
			//por si queremos hacer algo con el
			eq=equipamientoDAO.findById(equipamiento.getIdEquipo());
			return eq;
		}
		return eq;
	}

	@Override
	public Optional<Equipamiento> findEquipoById(ObjectId idEquipo) {
		return equipamientoDAO.findById(idEquipo);
	}

	@Override
	public List<Equipamiento> findAll() {
		return equipamientoDAO.findAll();
	}
}
