package esm.dnd.DnDDAM2EnriqueSergioMangel.servicio;

import java.util.List;
import java.util.Optional;

import org.bson.types.ObjectId;

import esm.dnd.DnDDAM2EnriqueSergioMangel.modelo.Jugador;

public interface IJugadorServicio {
	
	public boolean addJugador(Jugador jugador);
    
	public List<Jugador> findAllJugadores();
	
	public Jugador crearJugador(ObjectId idUsuario, ObjectId idFicha, Jugador jugador);

	public Optional<Jugador> findJugadorById(ObjectId idJugador);

	public boolean existsByIdJugador(ObjectId id);

	public Jugador deleteJugadorjeById(ObjectId id);

	public Iterable<Jugador> deleteAllJugadores();


}
