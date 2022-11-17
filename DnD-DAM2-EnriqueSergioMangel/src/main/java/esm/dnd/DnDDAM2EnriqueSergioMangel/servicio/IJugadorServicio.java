package esm.dnd.DnDDAM2EnriqueSergioMangel.servicio;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import esm.dnd.DnDDAM2EnriqueSergioMangel.modelo.Jugador;

public interface IJugadorServicio {
	
	public boolean addJugador(Jugador jugador);
    
	public List<Jugador> findAllJugadores();
	
	public Jugador crearJugador(UUID idUsuario, UUID idFicha, Jugador jugador);

	public Optional<Jugador> findJugadorById(UUID idJugador);

	public boolean existsByIdJugador(UUID id);

	public Jugador deleteJugadorjeById(UUID id);

	public Iterable<Jugador> deleteAllJugadores();


}
