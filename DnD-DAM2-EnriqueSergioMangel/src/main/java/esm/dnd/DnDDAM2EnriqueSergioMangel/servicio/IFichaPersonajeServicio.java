package esm.dnd.DnDDAM2EnriqueSergioMangel.servicio;

import java.util.List;
import java.util.Optional;

import org.bson.types.ObjectId;

import esm.dnd.DnDDAM2EnriqueSergioMangel.modelo.Caracteristica;
import esm.dnd.DnDDAM2EnriqueSergioMangel.modelo.Equipamiento;
import esm.dnd.DnDDAM2EnriqueSergioMangel.modelo.FichaPersonaje;
import esm.dnd.DnDDAM2EnriqueSergioMangel.modelo.Habilidad;

public interface IFichaPersonajeServicio {
    
public boolean addAllFichasPersonaje(List<FichaPersonaje> fichasPersonaje);
    
public boolean addFichaPersonaje(FichaPersonaje fichaPersonaje);

public List<FichaPersonaje> findAllFichasPersonaje();

public Optional<FichaPersonaje> findFichaPersonajeById(ObjectId idFichaPersonaje);

public boolean existsByIdFichaPersonaje(ObjectId id);

public FichaPersonaje deleteFichaPersonajeById(ObjectId id);

boolean actualizarFichaPersonaje(FichaPersonaje fichaPersonaje);

public Iterable<FichaPersonaje> deleteAllFichas();

public List<Caracteristica> getListaCaracteristicasPorId(ObjectId id);

public List<Equipamiento> getEquipamientoPorIdFicha(ObjectId idFicha);

public List<Habilidad> getHabilidadesPorIdFicha(ObjectId idFicha);
}
