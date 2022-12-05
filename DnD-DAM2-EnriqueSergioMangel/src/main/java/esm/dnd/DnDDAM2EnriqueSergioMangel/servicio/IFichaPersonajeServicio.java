package esm.dnd.DnDDAM2EnriqueSergioMangel.servicio;

import java.util.List;
import java.util.Optional;


import esm.dnd.DnDDAM2EnriqueSergioMangel.modelo.Caracteristica;
import esm.dnd.DnDDAM2EnriqueSergioMangel.modelo.Equipamiento;
import esm.dnd.DnDDAM2EnriqueSergioMangel.modelo.FichaPersonaje;
import esm.dnd.DnDDAM2EnriqueSergioMangel.modelo.Habilidad;

public interface IFichaPersonajeServicio {
    
public boolean addAllFichasPersonaje(List<FichaPersonaje> fichasPersonaje);
    
public boolean addFichaPersonaje(FichaPersonaje fichaPersonaje);

public List<FichaPersonaje> findAllFichasPersonaje();

public Optional<FichaPersonaje> findFichaPersonajeById(String idFichaPersonaje);

public boolean existsByIdFichaPersonaje(String id);

public FichaPersonaje deleteFichaPersonajeById(String id);

boolean actualizarFichaPersonaje(FichaPersonaje fichaPersonaje);

public Iterable<FichaPersonaje> deleteAllFichas();

public List<Caracteristica> getListaCaracteristicasPorId(String id);

public List<Equipamiento> getEquipamientoPorIdFicha(String idFicha);

public List<Habilidad> getHabilidadesPorIdFicha(String idFicha);
}
