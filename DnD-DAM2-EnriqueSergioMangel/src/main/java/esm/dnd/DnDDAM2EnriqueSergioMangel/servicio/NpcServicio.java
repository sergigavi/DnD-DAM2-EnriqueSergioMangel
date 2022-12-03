package esm.dnd.DnDDAM2EnriqueSergioMangel.servicio;

import java.util.List;
import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import esm.dnd.DnDDAM2EnriqueSergioMangel.modelo.Npc;
import esm.dnd.DnDDAM2EnriqueSergioMangel.repositorio.NpcRepository;

@Service
public class NpcServicio implements INpcServicio {

    @Autowired private NpcRepository npcDao;

    @Override
    public boolean existeNpc(ObjectId id) {
        return npcDao.existsById(id);
    }

    @Override
    public boolean insertarNpc(Npc npc) {
        if(!npcDao.existsById(npc.getIdNpc())){
            npcDao.save(npc);
            return true;
        }else
            return false;
    }

    @Override
    public boolean deleteNpc(ObjectId id) {
        if(npcDao.existsById(id)){
            npcDao.deleteById(id);
            return true;
        }else
            return false;
    }

    @Override
    public boolean editarNpc(Npc npc) {
        if(npcDao.existsById(npc.getIdNpc())){
            npcDao.save(npc);
            return true;
        }else
            return false;
    }

    @Override
    public Optional<Npc> findNpcById(ObjectId id) {
        
        return npcDao.findById(id);
    }

    @Override
    public List<Npc> findAllNpc() {
        
        return npcDao.findAll();
    }

    @Override
    public List<Npc> deleteAll() {
        
        List<Npc> npcs = npcDao.findAll();
        npcDao.deleteAll();
        return npcs;
    }
    
}
