package esm.dnd.DnDDAM2EnriqueSergioMangel.servicio;

import java.util.Optional;

import esm.dnd.DnDDAM2EnriqueSergioMangel.modelo.FichaPersonaje;

public interface IFichaPersonajeServicio {
    
public boolean addAllFichasPersonaje(Iterable<FichaPersonaje> fichasPersonaje);
    
    public boolean annadiFichaPersonaje(FichaPersonaje fichaPersonaje);

    public Iterable<FichaPersonaje> findAllFichasPersonaje();
    
    public Optional<FichaPersonaje> findFichaPersonajeById(String idFichaPersonaje);
    
    public boolean existsByIdFichaPersonaje(String id);
    
    public FichaPersonaje deleteFichaPersonajeById(String id);

    boolean actualizarFichaPersonaje(FichaPersonaje fichaPersonaje);

}
