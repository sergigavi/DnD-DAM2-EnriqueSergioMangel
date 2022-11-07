package esm.dnd.DnDDAM2EnriqueSergioMangel.servicio;

import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import esm.dnd.DnDDAM2EnriqueSergioMangel.modelo.FichaPersonaje;
import esm.dnd.DnDDAM2EnriqueSergioMangel.repositorio.FichaPersonajeRepository;

@Service
public class FichaPersonajeServicio implements IFichaPersonajeServicio {
    
    @Autowired private FichaPersonajeRepository fichaPersonajeDAO;

    @Override
    public boolean addAllFichasPersonaje(Iterable<FichaPersonaje> fichasPersonaje) {
        
        boolean exito = false;
        
        try {
            
            fichaPersonajeDAO.saveAll(fichasPersonaje);
            exito = true;
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return exito;
    }

    @Override
    public boolean annadiFichaPersonaje(FichaPersonaje fichaPersonaje) {
        
        boolean exito = false;
        
        if(!fichaPersonajeDAO.existsById(fichaPersonaje.getIdFichaPersonaje()))
        {
            try {
                fichaPersonajeDAO.save(fichaPersonaje);
                exito = true;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        
        return exito;
    }

    @Override
    public Iterable<FichaPersonaje> findAllFichasPersonaje() {
        return fichaPersonajeDAO.findAll();
    }

    @Override
    public Optional<FichaPersonaje> findFichaPersonajeById(UUID idFichaPersonaje) {
        return fichaPersonajeDAO.findById(idFichaPersonaje);
    }

    @Override
    public boolean existsByIdFichaPersonaje(UUID id) {
        return fichaPersonajeDAO.existsById(id);
    }

    @Override
    public FichaPersonaje deleteFichaPersonajeById(UUID id) {

        FichaPersonaje f = FichaPersonaje.builder().idFichaPersonaje(id).build();
        
        if (fichaPersonajeDAO.existsById(id)) {
            f = fichaPersonajeDAO.findById(id).get();
            fichaPersonajeDAO.deleteById(id);
        }
        
        return f;
    }

    @Override
    public boolean actualizarFichaPersonaje(FichaPersonaje fichaPersonaje) {
        
        Boolean exito = false;
        
        if (fichaPersonajeDAO.existsById(fichaPersonaje.getIdFichaPersonaje()))
        {
            try {
                
                fichaPersonajeDAO.save(fichaPersonaje);
                exito = true;
                
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        
        return exito;
    }

}
