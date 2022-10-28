package esm.dnd.DnDDAM2EnriqueSergioMangel.RESTControllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import esm.dnd.DnDDAM2EnriqueSergioMangel.modelo.Habilidad;
import esm.dnd.DnDDAM2EnriqueSergioMangel.services.IHabilidadService;

@CrossOrigin
@RestController
@RequestMapping("/dndtools/habilidades")
public class HabilidadController {
    
    @Autowired
    private IHabilidadService habilidadServicio;
    
    @GetMapping("/dametodas")
    public ResponseEntity<Iterable<Habilidad>> obtenerTodasLasHabilidades()
    {
        ResponseEntity<Iterable<Habilidad>> res = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        
        Iterable<Habilidad> allHabilidades = habilidadServicio.findAllHabilidades();
        
        res = new ResponseEntity<Iterable<Habilidad>>(allHabilidades, HttpStatus.OK);
        
        return res;
    }
    
    @PostMapping("/insertarPorParametros")
    public ResponseEntity<String> insertarHabilidad(@RequestParam String idHabilidad, @RequestParam String idFicha, @RequestParam String nombre, @RequestParam String competencia)
    {
        ResponseEntity<String> res = new ResponseEntity<>("Error insertando habilidad",HttpStatus.BAD_REQUEST);
        Habilidad h;
        try {
            
            if (!habilidadServicio.existsByIdHabilidad(idHabilidad)) {
                
                h = Habilidad.builder()
                        .idHabilidad(idHabilidad)
                        .idFicha(idFicha)
                        .nombre(nombre)
                        .competencia(competencia)
                        .build();
                
                habilidadServicio.annadiHabilidad(h);
                
                res = new ResponseEntity<String>("Habilidad insertada correctamente", HttpStatus.OK);
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return res;
    }

}
