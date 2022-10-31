package esm.dnd.DnDDAM2EnriqueSergioMangel.servicio;

import esm.dnd.DnDDAM2EnriqueSergioMangel.modelo.Equipamiento;

public interface IEquipamientoServicio {
              
    public boolean insertarEquipamiento(Equipamiento a);
	public boolean eliminarEquipamiento(String idEquipo);
	public boolean existeEquipamiento(String idEquipo);
}
