package esm.dnd.DnDDAM2EnriqueSergioMangel.services;

import java.util.Optional;

import esm.dnd.DnDDAM2EnriqueSergioMangel.modelo.FichaPersonaje;

public interface IFichaPersonajeService {
    
public boolean addAllFichasPersonaje(Iterable<FichaPersonaje> fichasPersonaje);
    
    public boolean annadiFichaPersonaje(FichaPersonaje fichaPersonaje);

    public Iterable<FichaPersonaje> findAllFichasPersonaje();
    
    public Optional<FichaPersonaje> findFichaPersonajeById(String idFichaPersonaje);
    
    public boolean existsByIdFichaPersonaje(String id);
    
    public FichaPersonaje deleteFichaPersonajeById(String id);

    boolean actualizarFichaPersonaje(FichaPersonaje fichaPersonaje);

}
