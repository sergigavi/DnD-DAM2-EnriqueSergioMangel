package esm.dnd.DnDDAM2EnriqueSergioMangel.servicio;

import java.util.List;
import java.util.Optional;


import esm.dnd.DnDDAM2EnriqueSergioMangel.modelo.Administrador;

public interface IAdministradorServicio {
        
    public boolean insertarAdministrador(Administrador a);
	public boolean eliminarAdministrador(String idAdmin);
	public boolean existeAdministrador(String idAdmin);
	public Iterable<Administrador> findAllAdmins();
	public Optional<Administrador> findAdminByID(String id);
	boolean insertarAdministradores(List<Administrador> admins);
	boolean existsByEmail(String email);
	Optional<Administrador> findByEmail(String email);
}
