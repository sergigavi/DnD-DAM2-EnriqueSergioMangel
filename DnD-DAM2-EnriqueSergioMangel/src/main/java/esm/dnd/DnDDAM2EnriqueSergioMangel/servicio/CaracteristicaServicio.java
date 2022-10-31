package esm.dnd.DnDDAM2EnriqueSergioMangel.servicio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import esm.dnd.DnDDAM2EnriqueSergioMangel.modelo.Caracteristica;
import esm.dnd.DnDDAM2EnriqueSergioMangel.repositorio.CaracteristicaRepository;

@Service
public class CaracteristicaServicio implements ICaracteristicaServicio{
    
    @Autowired CaracteristicaRepository caracteristicaDAO;

    @Override
	public boolean insertarCaracteristica(Caracteristica a) {
		boolean exito=false;
		
		if(!caracteristicaDAO.existsById(a.getIdHabilidad())) {
			caracteristicaDAO.save(a);
			exito=true;
		}
		
		return exito;
	}

	@Override
	public boolean eliminarAdministrador(String idHabilidad) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean existeAdministrador(String idHabilidad) {
		// TODO Auto-generated method stub
		return false;
	}

   
}
