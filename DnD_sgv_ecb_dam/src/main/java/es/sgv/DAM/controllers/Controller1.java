package es.sgv.DAM.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class Controller1 {
	
	@GetMapping("/pruebaController1")
	public String welcome(@RequestParam(name="nombre", required=false, defaultValue="Mundo") String nombre
			, Model model) //la clase Model guarda un map clave/valor
	{
		model.addAttribute("nombre", nombre);
		return "index"; //aqui va el nombre de la plantilla (sin extension) (lo demas lo hace el y busca en los directorios y todo)
	}

}
