package esm.dnd.DnDDAM2EnriqueSergioMangel.RESTControllers;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import org.bson.types.ObjectId;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import esm.dnd.DnDDAM2EnriqueSergioMangel.modelo.Alineamiento;
import esm.dnd.DnDDAM2EnriqueSergioMangel.modelo.Caracteristica;
import esm.dnd.DnDDAM2EnriqueSergioMangel.modelo.Clase;
import esm.dnd.DnDDAM2EnriqueSergioMangel.modelo.Equipamiento;
import esm.dnd.DnDDAM2EnriqueSergioMangel.modelo.FichaPersonaje;
import esm.dnd.DnDDAM2EnriqueSergioMangel.modelo.Habilidad;
import esm.dnd.DnDDAM2EnriqueSergioMangel.modelo.Raza;
import esm.dnd.DnDDAM2EnriqueSergioMangel.modelo.Usuario;
import esm.dnd.DnDDAM2EnriqueSergioMangel.servicio.FichaPersonajeServicio;
import esm.dnd.DnDDAM2EnriqueSergioMangel.servicio.UsuarioServicio;

@CrossOrigin(originPatterns = {"*"},methods = {RequestMethod.DELETE,RequestMethod.GET,RequestMethod.POST,RequestMethod.PUT,RequestMethod.OPTIONS})    //Con esta anotacion se salta el protocolo para poder acceder a la API desde el fetch de javascript etcetc
@RestController
@RequestMapping("/api/dndtools/personajes")
public class FichaPersonajeController {
    
    //Le inyecto otros servicios ya que aqui cargo los datos de todo    
    @Autowired private FichaPersonajeServicio fichaPersonajeServicio;
    
    @Autowired private UsuarioServicio usuarioServicio;

    
    @GetMapping("/getAll")
    public ResponseEntity<List<FichaPersonaje>> obtenerTodasLasFichasDePersonaje()
    {
        ResponseEntity<List<FichaPersonaje>> res = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        
        List<FichaPersonaje> allFichasPersonaje = fichaPersonajeServicio.findAllFichasPersonaje();
        
        res = new ResponseEntity<List<FichaPersonaje>>(allFichasPersonaje, HttpStatus.OK);
        
        return res;
    }
        
    @GetMapping("/getCaracteristicasById/{id}")
    public ResponseEntity<List<Caracteristica>> obtenerTodasLasFichasDePersonaje(@PathVariable ObjectId id)
    {
        ResponseEntity<List<Caracteristica>> res = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        if (fichaPersonajeServicio.existsByIdFichaPersonaje(id))
        {
            List<Caracteristica> caracteristicasDeLaFicha = fichaPersonajeServicio.getListaCaracteristicasPorId(id);
            
            res = new ResponseEntity<List<Caracteristica>>(caracteristicasDeLaFicha, HttpStatus.OK);
        }

        return res;
    }
    
    @PostMapping("/AddCaracteristicaEnFichaByIdFicha/{id}")
    public ResponseEntity<Caracteristica> addCaracteristicaByIdFicha(@PathVariable ObjectId id, @RequestBody Caracteristica caracteristica)
    {
        ResponseEntity<Caracteristica> res = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        
        if (fichaPersonajeServicio.existsByIdFichaPersonaje(id))
        {
        	FichaPersonaje ficha = fichaPersonajeServicio.findFichaPersonajeById(id).get();
        	
        	ficha.getCaracteristicas().add(caracteristica);
        	
        	fichaPersonajeServicio.actualizarFichaPersonaje(ficha);
            
            res = new ResponseEntity<Caracteristica>(caracteristica, HttpStatus.OK);
        }

        return res;
    }
    
    @PostMapping("/AddHabilidadEnFichaByIdFicha/{id}")
    public ResponseEntity<Habilidad> addHabilidadEnFichaByIdFicha(@PathVariable ObjectId id, @RequestBody Habilidad habilidad)
    {
        ResponseEntity<Habilidad> res = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        
        if (fichaPersonajeServicio.existsByIdFichaPersonaje(id))
        {
        	FichaPersonaje ficha = fichaPersonajeServicio.findFichaPersonajeById(id).get();
        	
        	ficha.getHabilidades().add(habilidad);
        	
        	fichaPersonajeServicio.actualizarFichaPersonaje(ficha);
            
            res = new ResponseEntity<Habilidad>(habilidad, HttpStatus.OK);
        }
        return res;
    }
    
    @GetMapping("/getHabilidadesById/{idFicha}")
    public ResponseEntity<List<Habilidad>> obtenerTodasLasHabilidades(@PathVariable ObjectId idFicha)
    {
        ResponseEntity<List<Habilidad>> res = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        
        if (fichaPersonajeServicio.existsByIdFichaPersonaje(idFicha))
        {
            List<Habilidad> habilidadesDeFicha = fichaPersonajeServicio.getHabilidadesPorIdFicha(idFicha);
            
            res = new ResponseEntity<List<Habilidad>>(habilidadesDeFicha, HttpStatus.OK);
        }

        return res;
    }

    @PostMapping("/AddEquipamientoEnFichaByIdFicha/{id}")
    public ResponseEntity<Equipamiento> addEquipamientoByIdFicha(@PathVariable ObjectId id, @RequestBody Equipamiento equipamiento)
    {
        ResponseEntity<Equipamiento> res = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        
        if (fichaPersonajeServicio.existsByIdFichaPersonaje(id))
        {
        	equipamiento.setIdEquipo(ObjectId.get());
        	FichaPersonaje ficha = fichaPersonajeServicio.findFichaPersonajeById(id).get();
        	
        	ficha.getInventario().add(equipamiento);
        	
        	fichaPersonajeServicio.actualizarFichaPersonaje(ficha);
            
            res = new ResponseEntity<Equipamiento>(equipamiento, HttpStatus.OK);
        }

        return res;
    }
    
    @GetMapping("/getEquipamientoById/{idFicha}")
    public ResponseEntity<List<Equipamiento>> obtenerTodosLosEquipamientos(@PathVariable ObjectId idFicha)
    {
        ResponseEntity<List<Equipamiento>> res = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        
        if (fichaPersonajeServicio.existsByIdFichaPersonaje(idFicha))
        {
            List<Equipamiento> equipamientoDeLaFicha = fichaPersonajeServicio.getEquipamientoPorIdFicha(idFicha);
            
            res = new ResponseEntity<List<Equipamiento>>(equipamientoDeLaFicha, HttpStatus.OK);
        }

        return res;
    }

    @PostMapping("/addFicha")
    public ResponseEntity<String> addFichaPersonaje(@RequestBody FichaPersonaje ficha){

        ResponseEntity<String> res = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        FichaPersonaje f=new FichaPersonaje();

        if(fichaPersonajeServicio.existsByIdFichaPersonaje(ficha.getIdFichaPersonaje())){
            return res;
        }

        f.setNivel(ficha.getNivel());

        //bonif comp
        f.setBonifCompetenciaPorNivel(ficha.getNivel());

        //caracteristicas
        List<Caracteristica> cars=new ArrayList<>();
        ficha.getCaracteristicas().stream().forEach((c)->{
            Caracteristica car = new Caracteristica(c.getNombre(),c.getValorTotal());
            cars.add(car);
        });
        f.setCaracteristicas(cars);

        /* 
        List<Habilidad> comp=new ArrayList<>();
        ficha.getHabilidades().stream().forEach((com)->{
            comp.add(com.getCompetencia());
        });
        f.getHabilidades().stream().forEach((h)->{
            
        });
        */

        f.setAlineamiento(ficha.getAlineamiento());
        f.setRaza(ficha.getRaza());
        f.setClase(ficha.getClase());
        f.setTransfondo(ficha.getTransfondo());
        f.setCa(ficha.getCa());
        f.setVelocidad(ficha.getVelocidad());
        f.setPuntosVidaMax(ficha.getPuntosVidaMax());
        //la ficha es nueva asi que empieza con toda la vida
        f.setPuntosVidaAct(ficha.getPuntosVidaMax());
        f.setRasgosPersonalidad(ficha.getRasgosPersonalidad());
        f.setIdeales(ficha.getIdeales());
        f.setVinculos(ficha.getVinculos());
        f.setDefectos(ficha.getDefectos());
        f.setRasgosAtt(ficha.getRasgosAtt());
        f.setOtrasComp(ficha.getOtrasComp());
        f.setApariencia(ficha.getApariencia());
        f.setHistoriaPersonal(ficha.getHistoriaPersonal());
        f.setRasgos(ficha.getRasgos());
        f.setNotasAdd(ficha.getNotasAdd());

        if(fichaPersonajeServicio.addFichaPersonaje(f)){
        return new ResponseEntity<String>("Exito al cargar la ficha",HttpStatus.OK);
        }else{
            return res;
        }

    }

    @PostMapping("/addFichaVacia")
    public ResponseEntity<String> addFichaPersonajeVacia(){

        ResponseEntity<String> res = new ResponseEntity<String>("Fallo al cargar la ficha",HttpStatus.BAD_REQUEST);

        FichaPersonaje f=new FichaPersonaje();

        if(fichaPersonajeServicio.addFichaPersonaje(f)){
            return new ResponseEntity<String>("Ficha cargada",HttpStatus.OK);
        }else{
            return res;
        }
    }
    
    @DeleteMapping("/deleteById")
	public ResponseEntity<FichaPersonaje> deleteFichaById(@RequestParam ObjectId id)
	{
		FichaPersonaje f;
		
		ResponseEntity<FichaPersonaje> res = new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
		
		if(fichaPersonajeServicio.existsByIdFichaPersonaje(id))
		{
			f = fichaPersonajeServicio.findFichaPersonajeById(id).get();
			fichaPersonajeServicio.deleteFichaPersonajeById(id);
			res = new ResponseEntity<FichaPersonaje>(f,HttpStatus.ACCEPTED);
		}
		
		return res;
		
	}
    
    @DeleteMapping("/deleteAll")
	public ResponseEntity<Iterable<FichaPersonaje>> deleteAllFichas()
	{
				
		ResponseEntity<Iterable<FichaPersonaje>> res = new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
		
		Iterable<FichaPersonaje> fichas = fichaPersonajeServicio.deleteAllFichas();
		
		res = new ResponseEntity<Iterable<FichaPersonaje>>(fichas,HttpStatus.ACCEPTED);
		
		return res;
		
	}
    
    @GetMapping("/cargarDatos")
    public ResponseEntity<String> cargar_Datos() {
        
        ResponseEntity<String> res = new ResponseEntity<>("Error insertando los datos", HttpStatus.BAD_REQUEST);

        try {

            cargarDatos();

            res = new ResponseEntity<String>("Datos insertados correctamente", HttpStatus.OK);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return res;
    }

    private void cargarDatos() {
        
        List<FichaPersonaje> fichasPersonaje = new ArrayList<>();
        
        List<Usuario> usuarios = new ArrayList<>();

        Usuario u1= Usuario.builder()
                .idUser(ObjectId.get())
                .nombre("Sergio")
                .apellidos("GV")
                .contrasenia("123abc")
                .nickname("GoSergus4")
                .biografia("")
                .email("sergio@sergio.es")
                .fechaNacimiento(LocalDate.of(2002, 10, 29))
                .urlImage("https://i.pinimg.com/736x/12/ed/5a/12ed5af107550e3a0e24792b41c47a56.jpg")
                .activo(false)
                .pais("España")
                .build();

        usuarios.add(u1);

        fichasPersonaje.add(FichaPersonaje.builder()
                .idFichaPersonaje(ObjectId.get())
                .usuario(u1)
                .nombre("Ganker el Escapante")
                .habilidades(List.of(
                    Habilidad.builder()
                .nombre("Mata Demonys")
                .competencia(true)
                .build()
                ))
                .clase(Clase.DRUIDA)
                .raza(Raza.TIFLIN)
                .alineamiento(Alineamiento.CAOTICO_NEUTRAL)
                .nivel(1)
                .bonifCompetencia(2)
                .transfondo("Mucho transfondo")
                .ca(10)
                .velocidad(6)
                .puntosVidaMax(8)
                .rasgosPersonalidad("inquieto")
                .ideales("ideal")
                .vinculos("vinculo")
                .defectos("defecto")
                .rasgosAtt("rasgos de una habilidad")
                .otrasComp("Herrmientas de ladron")
                .apariencia("Feo")
                .historiaPersonal("")
                .rasgos("rasgos")
                .notasAdd("")                
                .build());
        
        //
        
        cargarFichasPersonaje(fichasPersonaje);
        
        cargarUsuarios(usuarios);
        
    }

    private void cargarUsuarios(List<Usuario> usuarios) {
    	usuarioServicio.addAllUsuarios(usuarios);
	}

	private void cargarFichasPersonaje(List<FichaPersonaje> fichasPersonaje) {
        fichaPersonajeServicio.addAllFichasPersonaje(fichasPersonaje);
    }
}
