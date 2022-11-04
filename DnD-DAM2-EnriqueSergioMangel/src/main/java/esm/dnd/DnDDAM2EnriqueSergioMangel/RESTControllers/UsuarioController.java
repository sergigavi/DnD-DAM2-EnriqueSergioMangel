package esm.dnd.DnDDAM2EnriqueSergioMangel.RESTControllers;

//https://spring.io/guides/gs/securing-web/

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
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
    
}
