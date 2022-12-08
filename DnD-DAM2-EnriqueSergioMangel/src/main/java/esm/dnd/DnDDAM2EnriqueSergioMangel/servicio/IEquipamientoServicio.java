package esm.dnd.DnDDAM2EnriqueSergioMangel.servicio;

import java.util.List;
import java.util.Optional;


import esm.dnd.DnDDAM2EnriqueSergioMangel.modelo.Equipamiento;

public interface IEquipamientoServicio {
              
    public boolean insertarEquipamiento(Equipamiento a);
	public boolean eliminarEquipamiento(String idEquipo);
	public boolean existeEquipamiento(String idEquipo);
	public List<Equipamiento> getAll();
	public boolean eliminarEquipamientoTemp(String idEquipo);
	public Optional<Equipamiento> editarEquipo(Equipamiento equipamiento);
	public Optional<Equipamiento> findEquipoById(String idEquipo);
	public List<Equipamiento> findAll();
	public boolean insertarEquipamientos(List<Equipamiento> equipamientos);
	public boolean deleteAll();
	public long getCantidad();
}
