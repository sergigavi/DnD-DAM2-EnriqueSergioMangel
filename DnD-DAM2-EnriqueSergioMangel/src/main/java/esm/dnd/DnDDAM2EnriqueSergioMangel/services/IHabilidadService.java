package esm.dnd.DnDDAM2EnriqueSergioMangel.services;

import java.util.Optional;

import esm.dnd.DnDDAM2EnriqueSergioMangel.modelo.Habilidad;


public interface IHabilidadService {

    public boolean addAllHabilidades(Iterable<Habilidad> habilidades);
    
    public boolean annadiHabilidad(Habilidad habilidad);

    public Iterable<Habilidad> findAllHabilidades();
    
    public Optional<Habilidad> findHabilidadById(String idHabilidad);
    
    public boolean existsByIdHabilidad(String id);
    
    public Habilidad deleteHabilidadById(String id);

    boolean actualizarHabilidad(Habilidad habilidad);
}