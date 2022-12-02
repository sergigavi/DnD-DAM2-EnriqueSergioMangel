package esm.dnd.DnDDAM2EnriqueSergioMangel.servicio;

import java.util.List;
import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import esm.dnd.DnDDAM2EnriqueSergioMangel.modelo.Monstruo;
import esm.dnd.DnDDAM2EnriqueSergioMangel.repositorio.MonstruoRepository;

@Service
public class MonstruoServicio implements IMonstruoServicio {

    @Autowired private MonstruoRepository monstruoDao;

    @Override
    public boolean existeMonstruo(ObjectId id) {
        return monstruoDao.existsById(id);
    }

    @Override
    public boolean insertarMonstruo(Monstruo monstruo) {
        if(!monstruoDao.existsById(monstruo.getIdMonstruo())){
            monstruoDao.save(monstruo);
            return true;
        }else
            return false;
    }

    @Override
    public boolean deleteMonstruo(ObjectId id) {
        if(monstruoDao.existsById(id)){
            monstruoDao.deleteById(id);
            return true;
        }else
            return false;
    }

    @Override
    public boolean editarMonstruo(Monstruo monstruo) {
        if(monstruoDao.existsById(monstruo.getIdMonstruo())){
            monstruoDao.save(monstruo);
            return true;
        }else
            return false;
    }

    @Override
    public Optional<Monstruo> findMonstruoById(ObjectId id) {
        
        return monstruoDao.findById(id);
    }

    @Override
    public List<Monstruo> findAllMonstruo() {
        
        return monstruoDao.findAll();
    }

    @Override
    public List<Monstruo> deleteAll() {
        List<Monstruo> monstruos= monstruoDao.findAll();
        monstruoDao.deleteAll();
        return monstruos;
    }
    
}
