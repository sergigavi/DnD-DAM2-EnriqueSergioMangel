package esm.dnd.DnDDAM2EnriqueSergioMangel.servicio;

import org.bson.types.ObjectId;

import esm.dnd.DnDDAM2EnriqueSergioMangel.modelo.Equipamiento;

public interface IEquipamientoServicio {
              
    public boolean insertarEquipamiento(Equipamiento a);
	public boolean eliminarEquipamiento(ObjectId idEquipo);
	public boolean existeEquipamiento(ObjectId idEquipo);
}
