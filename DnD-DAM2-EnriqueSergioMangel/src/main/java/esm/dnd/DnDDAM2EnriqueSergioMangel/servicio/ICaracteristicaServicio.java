package esm.dnd.DnDDAM2EnriqueSergioMangel.servicio;

import esm.dnd.DnDDAM2EnriqueSergioMangel.modelo.Caracteristica;

public interface ICaracteristicaServicio {

    public boolean insertarCaracteristica(Caracteristica a);
	public boolean eliminarAdministrador(String idHabilidad);
	public boolean existeAdministrador(String idHabilidad);
}
