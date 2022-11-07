package esm.dnd.DnDDAM2EnriqueSergioMangel.servicio;

import java.util.Optional;
import java.util.UUID;

import esm.dnd.DnDDAM2EnriqueSergioMangel.modelo.FichaPersonaje;

public interface IFichaPersonajeServicio {
    
public boolean addAllFichasPersonaje(Iterable<FichaPersonaje> fichasPersonaje);
    
    public boolean annadiFichaPersonaje(FichaPersonaje fichaPersonaje);

    public Iterable<FichaPersonaje> findAllFichasPersonaje();
    
    public Optional<FichaPersonaje> findFichaPersonajeById(UUID idFichaPersonaje);
    
    public boolean existsByIdFichaPersonaje(UUID id);
    
    public FichaPersonaje deleteFichaPersonajeById(UUID id);

    boolean actualizarFichaPersonaje(FichaPersonaje fichaPersonaje);

}
