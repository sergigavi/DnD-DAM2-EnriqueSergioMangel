package esm.dnd.DnDDAM2EnriqueSergioMangel.servicio;

import java.util.List;
import java.util.Optional;

import org.bson.types.ObjectId;

import esm.dnd.DnDDAM2EnriqueSergioMangel.modelo.Partida;

public interface IPartidaServicio {
    
    public boolean existePartida(ObjectId idPartida);
    public Optional<Partida> findPartidaById(ObjectId idPartida);
    public List<Partida> findAll();
    public Optional<Partida> deletePartidaById(ObjectId idPartida);
    public boolean addPartida(Partida partida);
    public List<Partida> deleteAll();
    public Optional<Partida> editarPartida(Partida partida);
}
