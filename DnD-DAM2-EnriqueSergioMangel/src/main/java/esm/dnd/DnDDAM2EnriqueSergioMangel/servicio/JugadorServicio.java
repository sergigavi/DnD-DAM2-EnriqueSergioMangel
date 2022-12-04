package esm.dnd.DnDDAM2EnriqueSergioMangel.servicio;

import java.util.List;
import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import esm.dnd.DnDDAM2EnriqueSergioMangel.modelo.Jugador;
import esm.dnd.DnDDAM2EnriqueSergioMangel.repositorio.FichaPersonajeRepository;
import esm.dnd.DnDDAM2EnriqueSergioMangel.repositorio.JugadorRepository;
import esm.dnd.DnDDAM2EnriqueSergioMangel.repositorio.UsuarioRepository;

@Service
public class JugadorServicio implements IJugadorServicio {
	
	@Autowired
	private JugadorRepository jugadorDAO;
	
	@Autowired
	private UsuarioRepository usuarioDAO;
	
	@Autowired FichaPersonajeRepository fichaPersonajeDAO;

	@Override
	public boolean addJugador(Jugador jugador) {
		
		boolean exito = false;
		
		try {
			jugador.setIdJugador(ObjectId.get());
			jugadorDAO.save(jugador);
			exito = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return exito;
	}

	@Override
	public List<Jugador> findAllJugadores() {
		return jugadorDAO.findAll();
	}

	@Override
	public Jugador crearJugador(ObjectId idUsuario, ObjectId idFicha, Jugador jugador) {
		
		if( (usuarioDAO.existsById(idUsuario)) && (fichaPersonajeDAO.existsById(idFicha)) )
		{
			jugador.setIdUsuario(idUsuario);
			jugador.setIdFicha(idFicha);
			jugador.setIdJugador(ObjectId.get());
			jugadorDAO.save(jugador);
		}
		
		return jugador;
	}

	@Override
	public Optional<Jugador> findJugadorById(ObjectId idJugador) {
		return jugadorDAO.findById(idJugador);
	}

	@Override
	public boolean existsByIdJugador(ObjectId id) {
		return jugadorDAO.existsById(id);
	}

	@Override
	public Jugador deleteJugadorjeById(ObjectId id) {
		
		Jugador jugadorEliminao = Jugador.builder().build();
		
		if (jugadorDAO.existsById(id))
		{
			jugadorEliminao = jugadorDAO.findById(id).get();
			jugadorDAO.deleteById(id);
		}
		
		return jugadorEliminao;
	}

	@Override
	public Iterable<Jugador> deleteAllJugadores() {
		
		List<Jugador> jugadoresEliminados;
		
		jugadoresEliminados = jugadorDAO.findAll();
		jugadorDAO.deleteAll();
		
		return jugadoresEliminados;
	}

	@Override
	public boolean addJugadores(List<Jugador> jugadores) {
		boolean exito = false;
		
		try {
			jugadorDAO.saveAll(jugadores);
			exito = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return exito;
	}

}
