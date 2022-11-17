package esm.dnd.DnDDAM2EnriqueSergioMangel.servicio;

import java.util.Optional;
import java.util.UUID;

import org.bson.types.ObjectId;

import esm.dnd.DnDDAM2EnriqueSergioMangel.modelo.Administrador;

public interface IAdministradorServicio {
        
    public boolean insertarAdministrador(Administrador a);
	public boolean eliminarAdministrador(ObjectId idAdmin);
	public boolean existeAdministrador(ObjectId idAdmin);
	public Iterable<Administrador> findAllAdmins();
	public Optional<Administrador> findAdminByID(ObjectId id);

}
