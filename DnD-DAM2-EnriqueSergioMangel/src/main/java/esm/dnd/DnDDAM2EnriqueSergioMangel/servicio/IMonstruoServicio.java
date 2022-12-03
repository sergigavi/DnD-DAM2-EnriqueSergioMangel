package esm.dnd.DnDDAM2EnriqueSergioMangel.servicio;

import java.util.List;
import java.util.Optional;

import org.bson.types.ObjectId;

import esm.dnd.DnDDAM2EnriqueSergioMangel.modelo.Monstruo;

public interface IMonstruoServicio {
    
    public boolean existeMonstruo(ObjectId id);
    public boolean insertarMonstruo(Monstruo monstruo);
    public boolean deleteMonstruo(ObjectId id);
    public boolean editarMonstruo(Monstruo monstruo);
    public Optional<Monstruo> findMonstruoById(ObjectId id);
    public List<Monstruo> findAllMonstruo();
    public List<Monstruo> deleteAll();
}
