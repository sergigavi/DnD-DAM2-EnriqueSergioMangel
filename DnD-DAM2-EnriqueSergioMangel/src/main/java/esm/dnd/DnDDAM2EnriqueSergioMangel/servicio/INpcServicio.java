package esm.dnd.DnDDAM2EnriqueSergioMangel.servicio;

import java.util.List;
import java.util.Optional;

import org.bson.types.ObjectId;

import esm.dnd.DnDDAM2EnriqueSergioMangel.modelo.Npc;

public interface INpcServicio {
    
    public boolean existeNpc(ObjectId id);
    public boolean insertarNpc(Npc npc);
    public boolean deleteNpc(ObjectId id);
    public boolean editarNpc(Npc npc);
    public Optional<Npc> findNpcById(ObjectId id);
    public List<Npc> findAllNpc();
    public List<Npc> deleteAll();
}
