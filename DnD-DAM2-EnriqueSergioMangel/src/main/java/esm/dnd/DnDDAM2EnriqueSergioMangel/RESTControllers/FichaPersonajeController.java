package esm.dnd.DnDDAM2EnriqueSergioMangel.RESTControllers;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

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
        
        Set<Usuario> usuarios = new HashSet<>();

        Usuario u1= Usuario.builder()
                .idUser(UUID.randomUUID())
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

    private void cargarUsuarios(Set<Usuario> usuarios) {
    	usuarioServicio.addAllUsuarios(usuarios);
	}

	private void cargarFichasPersonaje(Set<FichaPersonaje> fichasPersonaje) {
        fichaPersonajeServicio.addAllFichasPersonaje(fichasPersonaje);
    }
}
