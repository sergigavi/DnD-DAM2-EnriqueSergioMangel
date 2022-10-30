package esm.dnd.DnDDAM2EnriqueSergioMangel.servicio;

import esm.dnd.DnDDAM2EnriqueSergioMangel.modelo.FichaPersonaje;

public interface IFichaPersonajeServicio {
                
    public boolean insertarFichaPersonaje(FichaPersonaje a);
	public boolean eliminarFichaPersonaje(String idFichaPersonaje);
	public boolean existeFichaPersonaje(String idFichaPersonaje);
}
