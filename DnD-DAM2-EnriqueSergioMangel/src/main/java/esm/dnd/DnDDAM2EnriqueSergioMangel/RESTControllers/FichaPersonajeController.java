package esm.dnd.DnDDAM2EnriqueSergioMangel.RESTControllers;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import esm.dnd.DnDDAM2EnriqueSergioMangel.modelo.Alineamiento;
import esm.dnd.DnDDAM2EnriqueSergioMangel.modelo.Caracteristica;
import esm.dnd.DnDDAM2EnriqueSergioMangel.modelo.Clase;
import esm.dnd.DnDDAM2EnriqueSergioMangel.modelo.FichaPersonaje;
import esm.dnd.DnDDAM2EnriqueSergioMangel.modelo.Habilidad;
import esm.dnd.DnDDAM2EnriqueSergioMangel.modelo.Raza;
import esm.dnd.DnDDAM2EnriqueSergioMangel.modelo.Usuario;
import esm.dnd.DnDDAM2EnriqueSergioMangel.servicio.IFichaPersonajeServicio;
import esm.dnd.DnDDAM2EnriqueSergioMangel.servicio.IUsuarioServicio;

@CrossOrigin    //Con esta anotacion se salta el protocolo para poder acceder a la API desde el fetch de javascript etcetc
@RestController
@RequestMapping("/api/dndtools/personajes")
public class FichaPersonajeController {
    
    //Le inyecto otros servicios ya que aqui cargo los datos de todo    
    @Autowired private IFichaPersonajeServicio fichaPersonajeServicio;
    
    @Autowired private IUsuarioServicio usuarioServicio;

    
    @GetMapping("/getAll")
    public ResponseEntity<List<FichaPersonaje>> obtenerTodasLasFichasDePersonaje()
    {
        ResponseEntity<List<FichaPersonaje>> res = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        
        List<FichaPersonaje> allFichasPersonaje = fichaPersonajeServicio.findAllFichasPersonaje();
        
        res = new ResponseEntity<List<FichaPersonaje>>(allFichasPersonaje, HttpStatus.OK);
        
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
                .idUser(UUID.randomUUID().toString())
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
                .idFichaPersonaje(UUID.randomUUID())
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
