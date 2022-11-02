package esm.dnd.DnDDAM2EnriqueSergioMangel.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import esm.dnd.DnDDAM2EnriqueSergioMangel.modelo.Habilidad;
import esm.dnd.DnDDAM2EnriqueSergioMangel.repositorio.HabilidadRepository;

@Service
public class HabilidadServiceImpl implements IHabilidadService {

    @Autowired private HabilidadRepository habilidadDAO;

    @Override
    public boolean addAllHabilidades(Iterable<Habilidad> habilidades) {
        
        boolean exito = false;
        
        try {
            
            habilidadDAO.saveAll(habilidades);
            exito = true;
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return exito;
    }

    @Override
    public boolean annadiHabilidad(Habilidad habilidad) {

        boolean exito = false;
        
        if(!habilidadDAO.existsById(habilidad.getIdHabilidad()))
        {
            try {
                habilidadDAO.save(habilidad);
                exito = true;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        
        return exito;
        
    }

    @Override
    public Iterable<Habilidad> findAllHabilidades() {
        return habilidadDAO.findAll();
    }

    @Override
    public Optional<Habilidad> findHabilidadById(String idHabilidad) {
        return habilidadDAO.findById(idHabilidad);
    }

    @Override
    public boolean existsByIdHabilidad(String id) {
        return habilidadDAO.existsById(id);
    }

    @Override
    public Habilidad deleteHabilidadById(String id) {
        
        Habilidad h = Habilidad.builder().idHabilidad(id).build();
        
        if (habilidadDAO.existsById(id)) {
            h = habilidadDAO.findById(id).get();
            habilidadDAO.deleteById(id);
        }
        
        return h;   //  En el caso de que no exista me devuelve uno vacio pero con ese id
    }

    @Override
    public boolean actualizarHabilidad(Habilidad habilidad) {

        Boolean exito = false;

        
        if (habilidadDAO.existsById(habilidad.getIdHabilidad()))
        {
            try {
                
                habilidadDAO.save(habilidad);
                exito = true;
                
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        
        return exito;
    }

}
