package esm.dnd.DnDDAM2EnriqueSergioMangel.servicio;

import esm.dnd.DnDDAM2EnriqueSergioMangel.modelo.Equipo_Personaje;

public interface IEquipoPersonajeServicio {
            
    public boolean insertarEquipoPersonaje(Equipo_Personaje a);
	public boolean eliminarEquipoPersonaje(String idEquipo);
	public boolean existeEquipoPersonaje(String idEquipo);
}
