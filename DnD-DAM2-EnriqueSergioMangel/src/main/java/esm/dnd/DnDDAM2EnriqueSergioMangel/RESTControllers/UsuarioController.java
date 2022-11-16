package esm.dnd.DnDDAM2EnriqueSergioMangel.RESTControllers;

//https://spring.io/guides/gs/securing-web/

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

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
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import esm.dnd.DnDDAM2EnriqueSergioMangel.modelo.Usuario;
import esm.dnd.DnDDAM2EnriqueSergioMangel.servicio.IUsuarioServicio;

@CrossOrigin(origins = {"*"},methods = {RequestMethod.DELETE,RequestMethod.GET,RequestMethod.POST,RequestMethod.PUT,RequestMethod.OPTIONS})
@RestController
@RequestMapping("/API/dndtools/usuarios")
public class UsuarioController {

	@Autowired
    private IUsuarioServicio usuarioServicio;
    
    @GetMapping("/dametodos")
    public ResponseEntity<List<Usuario>> obtenerTodosLosUsuarios()
    {
        ResponseEntity<List<Usuario>> res = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        
        List<Usuario> allUsuarios = usuarioServicio.findAllUsuarios();
        
        res = new ResponseEntity<List<Usuario>>(allUsuarios, HttpStatus.OK);
        
        return res;
    }

	
	@PostMapping("/addUsuario")
	public ResponseEntity<String> addUsuario(@RequestBody Usuario u){


		ResponseEntity<String> res = new ResponseEntity<>("Error al insertar usuario",HttpStatus.BAD_REQUEST);

		Usuario u1 = Usuario.builder().build();

		try {
			u1 = Usuario.builder()
				.idUser(UUID.randomUUID().toString())
				.nombre(u.getNombre())
				.apellidos(u.getApellidos())
				.contrasenia(u.getContrasenia())
				.nickname(u.getNickname())
				.biografia(u.getBiografia())
				.email(u.getEmail())
				.fechaNacimiento(u.getFechaNacimiento())
				.urlImage(u.getUrlImage())
				.pais(u.getPais())
				.build();

			usuarioServicio.insertarUsuario(u1);

			return new ResponseEntity<String>("Inserccion correcta",HttpStatus.OK);
			
		} catch (Exception e) {
			System.out.println(e.getStackTrace());
		}
		return res;
	}

    
    @PutMapping("/insertarPorParametro")
    public ResponseEntity<String> insertarPorParametros(@RequestParam String idUser, @RequestParam String nombre, @RequestParam String apellidos, @RequestParam String contrasenia, @RequestParam String nickname, @RequestParam String biografia, @RequestParam String email, @RequestParam String fechaNac, @RequestParam String urlImage, /*@RequestParam boolean activo, */@RequestParam String pais)
    {
    	ResponseEntity<String> res = new ResponseEntity<>("Error insertando usuario",HttpStatus.BAD_REQUEST);
    	
    	Usuario u = Usuario.builder().build();
    	
    	try {
    		
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
    	
    	Usuario u = Usuario.builder().build();
    	
    	try {
    		
			u = Usuario.builder()
					.idUser(UUID.randomUUID().toString())	//	Genero un id nuevo
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
}
