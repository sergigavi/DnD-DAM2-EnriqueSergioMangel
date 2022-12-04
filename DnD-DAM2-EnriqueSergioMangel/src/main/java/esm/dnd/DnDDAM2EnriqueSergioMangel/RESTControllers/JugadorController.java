package esm.dnd.DnDDAM2EnriqueSergioMangel.RESTControllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import esm.dnd.DnDDAM2EnriqueSergioMangel.servicio.IJugadorServicio;

@CrossOrigin
@RestController
@RequestMapping("/api/dndtools/jugadores")
public class JugadorController {
	
	@Autowired
	private IJugadorServicio jugadorServicio;
	
	//aqui en el delete poner que se elimine tambien de la fichapersonaje el id
	
	
}
