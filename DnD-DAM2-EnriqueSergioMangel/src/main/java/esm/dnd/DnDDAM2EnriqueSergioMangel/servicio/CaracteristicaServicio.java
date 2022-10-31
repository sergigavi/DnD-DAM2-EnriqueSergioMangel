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
	public boolean eliminarCaracteristica(String idHabilidad) {
		boolean exito=false;
		
		if(caracteristicaDAO.existsById(idHabilidad)) {
			caracteristicaDAO.deleteById(idHabilidad);
			exito=true;
		}
			
		return exito;
	}

	@Override
	public boolean existeCaracteristica(String idHabilidad) {
		return caracteristicaDAO.existsById(idHabilidad);
	}

   
}
