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
			/* 
			if(!personajes.isEmpty()){
				System.out.println("porq");
				personajes.stream()
					.flatMap(p->p.getInventario().stream()).forEach(e->{
						if(e.getIdEquipo().equals(equipamiento.getIdEquipo())){
							e=equipamiento;
						}
					});
				fichaPersonajeDAO.saveAll(personajes);
			}
			*/
			System.out.println("Casi");
			Equipamiento e = equipamientoDAO.findById(equipamiento.getIdString()).get();
			System.out.println("Encontradox2");
			e.setNombre(equipamiento.getNombre());
			e.setDanio(equipamiento.getDanio());
			e.setAlcance(equipamiento.getAlcance());
			e.setCategoria(equipamiento.getCategoria());
			e.setDescripcion(equipamiento.getDescripcion());
			e.setModificador(equipamiento.getModificador());
			e.setPeso(equipamiento.getPeso());
			e.setPropiedad(equipamiento.getPropiedad());
			e.setTipo(equipamiento.getTipo());
			equipamientoDAO.save(e);
			//Esta linea es redundante, pero con ella sacamos el objeto acualizado de la base de datos
			//por si queremos hacer algo con el
			eq=equipamientoDAO.findById(e.getIdString());
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
}
