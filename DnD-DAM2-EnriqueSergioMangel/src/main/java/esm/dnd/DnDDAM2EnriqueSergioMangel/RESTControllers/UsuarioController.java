package esm.dnd.DnDDAM2EnriqueSergioMangel.RESTControllers;

//https://spring.io/guides/gs/securing-web/

import java.time.LocalDate;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import esm.dnd.DnDDAM2EnriqueSergioMangel.modelo.Usuario;
import esm.dnd.DnDDAM2EnriqueSergioMangel.servicio.IUsuarioServicio;

@CrossOrigin
@RestController
@RequestMapping("/API/dndtools/usuarios")
public class UsuarioController {

	@Autowired
    private IUsuarioServicio usuarioServicio;
    
    @GetMapping("/dametodos")
    public ResponseEntity<Iterable<Usuario>> obtenerTodosLosUsuarios()
    {
        ResponseEntity<Iterable<Usuario>> res = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        
        Iterable<Usuario> allUsuarios = usuarioServicio.findAllUsuarios();
        
        res = new ResponseEntity<Iterable<Usuario>>(allUsuarios, HttpStatus.OK);
        
        return res;
    }

	@PostMapping("/insertarUsuario")
    public ResponseEntity<String> insertarUsuario(@RequestBody Usuario usuario)
    {
    	ResponseEntity<String> res = new ResponseEntity<>("Error insertando usuario",HttpStatus.BAD_REQUEST);
    	    	
    	try {
			if (usuarioServicio.existeUsuario(usuario.getIdUser()))
			{
				usuario.setIdUser(generarIdUser());
			}else {
				usuarioServicio.insertarUsuario(usuario);
				res = new ResponseEntity<>("Usuario insertado correctamente", HttpStatus.OK);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
    	
    	return res;
    }

    
    @PostMapping("/insertarPorParametro")
    public ResponseEntity<String> insertarPorParametros(@RequestParam String idUser, @RequestParam String nombre, @RequestParam String apellidos, @RequestParam String contrasenia, @RequestParam String nickname, @RequestParam String biografia, @RequestParam String email, @RequestParam String fechaNac, @RequestParam String urlImage, /*@RequestParam boolean activo, */@RequestParam String pais)
    {
    	ResponseEntity<String> res = new ResponseEntity<>("Error insertando usuario",HttpStatus.BAD_REQUEST);
    	
    	try {

			Usuario u ;
    		
    		if (!usuarioServicio.existeUsuario(idUser))
    		{
    			u = Usuario.builder()
    					.idUser(idUser)
    					.nombre(nombre)
    					.apellidos(apellidos)
    					.contrasenia(contrasenia)
    					.nickname(nickname)
    					.biografia(biografia)
    					.email(email)
    					.fechaNacimiento(LocalDate.parse(fechaNac))
    					.urlImage(urlImage)
    					//.activo(activo)
    					.pais(pais)
    					.build();
    			
    			usuarioServicio.insertarUsuario(u);
    			
    			res = new ResponseEntity<>("Usuario insertado correctamente", HttpStatus.OK);
    		}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
    	
    	return res;
    }
    
    @PutMapping("/insertarPorURL/{nombre}/{apellidos}/{nickname}/{email}")
    public ResponseEntity<String> insertarPorURL(@PathVariable String nombre, @PathVariable String apellidos, @PathVariable String nickname, @PathVariable String email)
    {
    	ResponseEntity<String> res = new ResponseEntity<>("Error insertando usuario",HttpStatus.BAD_REQUEST);
    	
    	try {
    		
			Usuario u;

			u = Usuario.builder()
					.idUser(generarIdUser())	//	Genero un id nuevo
					.nombre(nombre)
					.apellidos(apellidos)
					//.contrasenia(contrasenia)
					.nickname(nickname)
					//.biografia(biografia)
					.email(email)
					//.fechaNacimiento(LocalDate.parse(fechaNac))
					//.urlImage(urlImage)
					//.activo(activo)
					//.pais(pais)
					.build();
			
			usuarioServicio.insertarUsuario(u);
			
			res = new ResponseEntity<>("Usuario insertado correctamente", HttpStatus.OK);
    		
			
		} catch (Exception e) {
			e.printStackTrace();
		}
    	
    	return res;
    }


	private String generarIdUser() {

		Set<Usuario> usuarios = (Set<Usuario>) usuarioServicio.findAllUsuarios();
		
		String lastId = usuarios.stream()
		.sorted((u1, u2) -> u1.getIdUser().compareTo(u2.getIdUser()))
		.map(u -> u.getIdUser())
		.findFirst().get();
		
		//cojo todo el id ultimo menos el ultimo digito y se lo sumo al ultimo digito mas uno pasado a string
		return lastId.substring(0,lastId.length()-1) + Integer.toString(Integer.parseInt(lastId.substring(lastId.length()-1)) + 1);		
		
	}
    
}
