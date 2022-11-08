package esm.dnd.DnDDAM2EnriqueSergioMangel.servicio;

import java.util.Optional;
import java.util.UUID;

import esm.dnd.DnDDAM2EnriqueSergioMangel.modelo.Administrador;

public interface IAdministradorServicio {
        
    public boolean insertarAdministrador(Administrador a);
	public boolean eliminarAdministrador(UUID idAdmin);
	public boolean existeAdministrador(UUID idAdmin);
	public Iterable<Administrador> findAllAdmins();
	public Optional<Administrador> findAdminByID(UUID id);

}
