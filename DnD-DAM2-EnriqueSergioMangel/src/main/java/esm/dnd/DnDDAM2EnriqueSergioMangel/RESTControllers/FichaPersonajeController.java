package esm.dnd.DnDDAM2EnriqueSergioMangel.RESTControllers;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import esm.dnd.DnDDAM2EnriqueSergioMangel.modelo.Alineamiento;
import esm.dnd.DnDDAM2EnriqueSergioMangel.modelo.Clase;
import esm.dnd.DnDDAM2EnriqueSergioMangel.modelo.FichaPersonaje;
import esm.dnd.DnDDAM2EnriqueSergioMangel.modelo.Habilidad;
import esm.dnd.DnDDAM2EnriqueSergioMangel.modelo.Raza;
import esm.dnd.DnDDAM2EnriqueSergioMangel.services.IFichaPersonajeService;
import esm.dnd.DnDDAM2EnriqueSergioMangel.services.IHabilidadService;

@CrossOrigin    //Con esta anotacion se salta el protocolo para poder acceder a la API desde el fetch de javascript etcetc
@RestController
@RequestMapping("/dndtools/personajes")
public class FichaPersonajeController {
    
    //Le inyecto otros servicios ya que aqui cargo los datos de todo
    @Autowired private IHabilidadService habilidadServicio;
    
    @Autowired private IFichaPersonajeService fichaPersonajeServicio;
    
    @GetMapping("/dametodos")
    public ResponseEntity<Iterable<FichaPersonaje>> obtenerTodasLasFichasDePersonaje()
    {
        ResponseEntity<Iterable<FichaPersonaje>> res = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        
        Iterable<FichaPersonaje> allFichasPersonaje = fichaPersonajeServicio.findAllFichasPersonaje();
        
        res = new ResponseEntity<Iterable<FichaPersonaje>>(allFichasPersonaje, HttpStatus.OK);
        
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
        
        Set<FichaPersonaje> fichasPersonaje = new HashSet<>();

        Set<Habilidad> habilidades = new HashSet<>();
        
        cargarFichasPersonaje(fichasPersonaje);
        
        cargarHabilidades(habilidades);       
        
    }



    private void cargarFichasPersonaje(Set<FichaPersonaje> fichasPersonaje) {
        
        fichasPersonaje.add(FichaPersonaje.builder()
                .idFichaPersonaje("C_fp_1")
                .idUsuario("C_us_1")
                .nombre("Ganker el Escapante")
                .clase(Clase.DRUIDA)
                .raza(Raza.TIFLIN)
                .alineamiento(Alineamiento.CAOTICO_NEUTRAL)
                .nivel(4)
                .transfondo("Mucho transfondo")
                .nombreJugador("Sergio Garcia")
                .ca("")
                .velocidad(6)
                .puntosVidaMax(8)
                .puntosVidaActuales(6)
                .rasgosPersonalidades(Set.of("dicharachero","simpatico", "inquieto"))
                .ideales(Set.of("ideal1","ideal2"))
                .vinculos(Set.of("vinculo1","vinculo2", "vinculo3"))
                .defectos(Set.of("defecto1","defecto2", "defecto3"))
                .rasgosAtt(Set.of("rasgo1","rasgo2", "rasgo3"))
                .otrasComp(Set.of("1","2", "3"))
                .apariencia(Set.of("r1","r2", "r3"))
                .historiaPersonal("")
                .rasgos(Set.of("rs1","rs2", "rs3"))
                .notasAdd("")                
                .build());
        
        fichaPersonajeServicio.addAllFichasPersonaje(fichasPersonaje);
        
    }

    private void cargarHabilidades(Set<Habilidad> habilidades) {
        
        
        habilidades.add(Habilidad.builder()
                .idHabilidad("C_h_1")
                .idFicha("C_fp_1")
                .nombre("Mata Demonys")
                .competencia("matar demonios")
                .build());
        
        habilidadServicio.addAllHabilidades(habilidades);
        
    }

}
