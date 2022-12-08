package esm.dnd.DnDDAM2EnriqueSergioMangel.servicio;

import java.util.List;
import java.util.Optional;

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
	public boolean eliminarEquipamiento(String idEquipo) {
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
					.filter(i->!i.isEmpty())
					.filter(i->i.contains(e))
					.forEach(i->i.remove(e));
				fichaPersonajeDAO.saveAll(personajes);
			}
			if(!partidas.isEmpty()){
				partidas.stream()
					.flatMap(p->p.getFichasPartida().stream())
					.map(f->f.getInventario())
					.filter(i->!i.isEmpty())
					.filter(i->i.contains(e))
					.forEach(i->i.remove(e));
				partidaDAO.saveAll(partidas);
			}
			equipamientoDAO.delete(e);
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
		
		if(!equipamientoDAO.existsById(a.getIdString())) {
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
	public boolean eliminarEquipamientoTemp(String idEquipo) {
		
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
		if(equipamientoDAO.existsById(equipamiento.getIdString())){
			List<FichaPersonaje> personajes=fichaPersonajeDAO.findAll();
			List<Partida> partidas=partidaDAO.findAll();
			Equipamiento equipoAntiguo = equipamientoDAO.findById(equipamiento.getIdString()).get();
			Equipamiento equipoNuevo = new Equipamiento();

			equipoNuevo.setIdEquipo(equipoAntiguo.getIdEquipo());
			equipoNuevo.setIdString(equipoAntiguo.getIdString());
			equipoNuevo.setNombre(equipamiento.getNombre());
			equipoNuevo.setDanio(equipamiento.getDanio());
			equipoNuevo.setAlcance(equipamiento.getAlcance());
			equipoNuevo.setCategoria(equipamiento.getCategoria());
			equipoNuevo.setDescripcion(equipamiento.getDescripcion());
			equipoNuevo.setModificador(equipamiento.getModificador());
			equipoNuevo.setPeso(equipamiento.getPeso());
			equipoNuevo.setPropiedad(equipamiento.getPropiedad());
			equipoNuevo.setTipo(equipamiento.getTipo());

			if(!personajes.isEmpty()){
				personajes.stream()
					.map(p->p.getInventario())
					.filter(i->!i.isEmpty())
					.filter(i->i.contains(equipoAntiguo))
					.forEach(i->{
						i.remove(equipoAntiguo);
						i.add(equipoNuevo);
					});
				fichaPersonajeDAO.saveAll(personajes);
			}
			if(!partidas.isEmpty()){
				partidas.stream()
					.flatMap(p->p.getFichasPartida().stream())
					.map(f->f.getInventario())
					.filter(i->!i.isEmpty())
					.filter(i->i.contains(equipoAntiguo))
					.forEach(i->{
						i.remove(equipoAntiguo);
						i.add(equipoNuevo);
					});
				partidaDAO.saveAll(partidas);
			}

			equipamientoDAO.save(equipoNuevo);
			//Esta linea es redundante, pero con ella sacamos el objeto acualizado de la base de datos
			//por si queremos hacer algo con el
			eq=equipamientoDAO.findById(equipoNuevo.getIdString());
			return eq;
		}
		return eq;
	}

	@Override
	public Optional<Equipamiento> findEquipoById(String idEquipo) {
		return equipamientoDAO.findById(idEquipo);
	}

	@Override
	public List<Equipamiento> findAll() {
		return equipamientoDAO.findAll();
	}

	@Override
	public boolean insertarEquipamientos(List<Equipamiento> equipamientos) {

		boolean exito = false;
		
		try {
			equipamientoDAO.saveAll(equipamientos);
			exito = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return exito;
	}

	@Override
	public boolean deleteAll() {
		boolean exito=false;
		try {
			equipamientoDAO.deleteAll();
			exito=true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return exito;
	}
}
