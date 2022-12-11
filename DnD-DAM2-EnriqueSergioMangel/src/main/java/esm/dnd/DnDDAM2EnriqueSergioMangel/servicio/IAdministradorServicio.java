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
	public boolean insertarAdministradores(List<Administrador> admins);
	public boolean existsByEmail(String email);
	public Optional<Administrador> findByEmail(String email);
}
