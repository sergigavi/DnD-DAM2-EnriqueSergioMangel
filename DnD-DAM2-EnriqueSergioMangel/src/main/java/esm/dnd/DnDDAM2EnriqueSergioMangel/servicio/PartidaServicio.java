package esm.dnd.DnDDAM2EnriqueSergioMangel.servicio;

import java.util.List;
import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import esm.dnd.DnDDAM2EnriqueSergioMangel.modelo.Partida;
import esm.dnd.DnDDAM2EnriqueSergioMangel.repositorio.PartidaRepository;

@Service
public class PartidaServicio implements IPartidaServicio {

    @Autowired private PartidaRepository partidaDAO;

    @Override
    public boolean existePartida(ObjectId idPartida) {
        return partidaDAO.existsById(idPartida);
    }

    @Override
    public Optional<Partida> findPartidaById(ObjectId idPartida) {
        return partidaDAO.findById(idPartida);
    }

    @Override
    public List<Partida> findAll() {
        return partidaDAO.findAll();
    }

    @Override
    public Optional<Partida> deletePartidaById(ObjectId idPartida) {
        if(partidaDAO.existsById(idPartida)){
            Optional<Partida> p=partidaDAO.findById(idPartida);
            partidaDAO.deleteById(idPartida);
            return p;
        }else
            return Optional.empty();
    }

    @Override
    public boolean addPartida(Partida partida) {
        if(!partidaDAO.existsById(partida.getIdPartida())){
            partidaDAO.save(partida);
            return true;
        }else
            return false;
    }

    @Override
    public List<Partida> deleteAll() {
        List<Partida> partidas= partidaDAO.findAll();
        partidaDAO.deleteAll();
        return partidas;
    }

    @Override
    public Optional<Partida> editarPartida(Partida partida) {
        if(partidaDAO.existsById(partida.getIdPartida())){
            partidaDAO.save(partida);
            Optional<Partida> p=partidaDAO.findById(partida.getIdPartida());
            return p;
        }else
            return Optional.empty();
    }
    
}
