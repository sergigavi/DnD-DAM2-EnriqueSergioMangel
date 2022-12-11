package esm.dnd.DnDDAM2EnriqueSergioMangel.servicio;

import java.util.List;
import java.util.Optional;


import esm.dnd.DnDDAM2EnriqueSergioMangel.modelo.Partida;
import esm.dnd.DnDDAM2EnriqueSergioMangel.modelo.Usuario;

public interface IPartidaServicio {
    
    public boolean existePartida(String idPartida);
    public Optional<Partida> findPartidaById(String idPartida);
    public List<Partida> findAll();
    public Optional<Partida> deletePartidaById(String idPartida);
    public boolean addPartida(Partida partida);
    public List<Partida> deleteAll();
    public Optional<Partida> editarPartida(Partida partida);
}
