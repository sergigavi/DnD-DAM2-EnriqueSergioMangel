package esm.dnd.DnDDAM2EnriqueSergioMangel.servicio;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import esm.dnd.DnDDAM2EnriqueSergioMangel.modelo.Caracteristica;
import esm.dnd.DnDDAM2EnriqueSergioMangel.modelo.Equipamiento;
import esm.dnd.DnDDAM2EnriqueSergioMangel.modelo.FichaPersonaje;
import esm.dnd.DnDDAM2EnriqueSergioMangel.modelo.Habilidad;
import esm.dnd.DnDDAM2EnriqueSergioMangel.repositorio.FichaPersonajeRepository;

@Service
public class FichaPersonajeServicio implements IFichaPersonajeServicio {
    
    @Autowired private FichaPersonajeRepository fichaPersonajeDAO;
    @Override
    public boolean addAllFichasPersonaje(List<FichaPersonaje> fichasPersonaje) {
        
        boolean exito = false;
        
        try {
            
            fichaPersonajeDAO.saveAll(fichasPersonaje);
            exito = true;
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return exito;
    }

    @Override
    public boolean addFichaPersonaje(FichaPersonaje fichaPersonaje) {
        
        boolean exito = false;
        
        if(!fichaPersonajeDAO.existsById(fichaPersonaje.getIdFichaPersonajeString()))
        {
            try {
                fichaPersonajeDAO.save(fichaPersonaje);
                exito = true;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        
        return exito;
    }

    @Override
    public List<FichaPersonaje> findAllFichasPersonaje() {
        return fichaPersonajeDAO.findAll();
    }

    @Override
    public Optional<FichaPersonaje> findFichaPersonajeById(String idFichaPersonaje) {
        return fichaPersonajeDAO.findById(idFichaPersonaje);
    }

    @Override
    public boolean existsByIdFichaPersonaje(String id) {
        return fichaPersonajeDAO.existsById(id);
    }

    @Override
    public FichaPersonaje deleteFichaPersonajeById(String id) {

        FichaPersonaje f = FichaPersonaje.builder()
        .idFichaPersonajeString(id)
        .build();
        
        if (fichaPersonajeDAO.existsById(id)) {
            f = fichaPersonajeDAO.findById(id).get();
            fichaPersonajeDAO.deleteById(id);
        }
        
        return f;
    }

    @Override
	public long getCantidad() {
		return fichaPersonajeDAO.count();
	}

    @Override
    public boolean actualizarFichaPersonaje(FichaPersonaje fichaPersonaje) {
        
        Boolean exito = false;
        
        if (fichaPersonajeDAO.existsById(fichaPersonaje.getIdFichaPersonajeString()))
        {
            try {

                FichaPersonaje f= new FichaPersonaje();

                FichaPersonaje fi = fichaPersonajeDAO.findById(fichaPersonaje.getIdFichaPersonajeString()).get();

                f.setIdFichaPersonaje(fi.getIdFichaPersonaje());
                f.setIdFichaPersonajeString(fichaPersonaje.getIdFichaPersonajeString());
                f.setNombre(fichaPersonaje.getNombre());
                f.setNivel(fichaPersonaje.getNivel());
                f.setBonifCompetencia(f.getNivel());

                List<Caracteristica> cars=new ArrayList<>();
                fichaPersonaje.getCaracteristicas().stream().forEach((c)->{
                    Caracteristica car = new Caracteristica(c.getNombre(),c.getValorTotal());
                    cars.add(car);
                });
                
                f.setCaracteristicas(cars);

                List<Habilidad> habs = f.setHabilidadesNuevas(f.getCaracteristicas(),fichaPersonaje.getHabilidades(),f.getBonifCompetencia());
                f.setHabilidades(habs);

                f.setAlineamiento(fichaPersonaje.getAlineamiento());
                f.setRaza(fichaPersonaje.getRaza());
                f.setClase(fichaPersonaje.getClase());
                f.setTransfondo(fichaPersonaje.getTransfondo());
                f.setCa(fichaPersonaje.getCa());
                f.setVelocidad(fichaPersonaje.getVelocidad());
                f.setPuntosVidaMax(fichaPersonaje.getPuntosVidaMax());
                //la ficha es nueva asi que empieza con toda la vida
                f.setPuntosVidaAct(fichaPersonaje.getPuntosVidaMax());
                f.setRasgosPersonalidad(fichaPersonaje.getRasgosPersonalidad());
                f.setIdeales(fichaPersonaje.getIdeales());
                f.setVinculos(fichaPersonaje.getVinculos());
                f.setDefectos(fichaPersonaje.getDefectos());
                f.setRasgosAtt(fichaPersonaje.getRasgosAtt());
                f.setOtrasComp(fichaPersonaje.getOtrasComp());
                f.setApariencia(fichaPersonaje.getApariencia());
                f.setHistoriaPersonal(fichaPersonaje.getHistoriaPersonal());
                f.setRasgos(fichaPersonaje.getRasgos());
                f.setNotasAdd(fichaPersonaje.getNotasAdd());
                f.setNombre(fichaPersonaje.getNombre());
                f.setInventario(fichaPersonaje.getInventario());
                
                fichaPersonajeDAO.save(f);
                exito = true;
                
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        
        return exito;
    }

	@Override
	public Iterable<FichaPersonaje> deleteAllFichas() {

		Iterable<FichaPersonaje> fichas = fichaPersonajeDAO.findAll();
		fichaPersonajeDAO.deleteAll();
		return fichas;
	}

	@Override
	public List<Caracteristica> getListaCaracteristicasPorId(String id) {

		return fichaPersonajeDAO.findById(id).get().getCaracteristicas();
	}

	@Override
	public List<Equipamiento> getEquipamientoPorIdFicha(String idFicha) {
		
		FichaPersonaje ficha;
		
		List<Equipamiento> listaEquipamiento = new ArrayList<Equipamiento>();
		
		if (fichaPersonajeDAO.existsById(idFicha))
		{
			ficha = fichaPersonajeDAO.findById(idFicha).get();
			listaEquipamiento = ficha.getInventario();
		}
		
		return listaEquipamiento;
	}

	@Override
	public List<Habilidad> getHabilidadesPorIdFicha(String idFicha) {

		FichaPersonaje ficha;
		List<Habilidad> habilidadesFicha = new ArrayList<>();
		
		if (fichaPersonajeDAO.existsById(idFicha))
		{
			ficha = fichaPersonajeDAO.findById(idFicha).get();
			habilidadesFicha = ficha.getHabilidades();
		}
		
		return habilidadesFicha;
	}
}
