package esm.dnd.DnDDAM2EnriqueSergioMangel.RESTControllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import esm.dnd.DnDDAM2EnriqueSergioMangel.modelo.Caracteristica;
import esm.dnd.DnDDAM2EnriqueSergioMangel.servicio.IFichaPersonajeServicio;

@CrossOrigin
@RestController
@RequestMapping("/api/dndtools/caracteristicas")
public class CaracteristicaController {

	@Autowired
	private IFichaPersonajeServicio fichaPersonajeService;
	
	@GetMapping("/getAll")
    public ResponseEntity<Iterable<Caracteristica>> obtenerTodasLasCaracteristicas()
    {
        ResponseEntity<Iterable<Caracteristica>> res = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        
        List<Caracteristica> allCaracteristicas = new ArrayList<>();
        
        fichaPersonajeService.findAllFichasPersonaje().stream()
        .forEach(f -> allCaracteristicas.addAll(f.getCaracteristicas()));

        res = new ResponseEntity<Iterable<Caracteristica>>(allCaracteristicas, HttpStatus.OK);
        
        return res;
    }
}
