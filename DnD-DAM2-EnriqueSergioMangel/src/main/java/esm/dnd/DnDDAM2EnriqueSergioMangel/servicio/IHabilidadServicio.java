package esm.dnd.DnDDAM2EnriqueSergioMangel.servicio;

import esm.dnd.DnDDAM2EnriqueSergioMangel.modelo.Habilidad;

public interface IHabilidadServicio {
    
    public boolean insertarHabilidad(Habilidad a);
	public boolean eliminarHabilidad(String idHabilidad);
	public boolean existeHabilidad(String idHabilidad);
}
