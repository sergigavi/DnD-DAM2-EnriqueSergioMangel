package esm.dnd.DnDDAM2EnriqueSergioMangel.servicio;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import esm.dnd.DnDDAM2EnriqueSergioMangel.modelo.FichaPersonaje;

public interface IFichaPersonajeServicio {
    
public boolean addAllFichasPersonaje(List<FichaPersonaje> fichasPersonaje);
    
public boolean addFichaPersonaje(FichaPersonaje fichaPersonaje);

public List<FichaPersonaje> findAllFichasPersonaje();

public Optional<FichaPersonaje> findFichaPersonajeById(UUID idFichaPersonaje);

public boolean existsByIdFichaPersonaje(UUID id);

public FichaPersonaje deleteFichaPersonajeById(UUID id);

boolean actualizarFichaPersonaje(FichaPersonaje fichaPersonaje);

public Iterable<FichaPersonaje> deleteAllFichas();

}
