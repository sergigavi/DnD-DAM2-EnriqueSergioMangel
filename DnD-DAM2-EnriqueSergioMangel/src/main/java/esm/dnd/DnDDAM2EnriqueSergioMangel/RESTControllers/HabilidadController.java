package esm.dnd.DnDDAM2EnriqueSergioMangel.RESTControllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
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

}
