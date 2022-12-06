package esm.dnd.DnDDAM2EnriqueSergioMangel.RESTControllers;

//https://spring.io/guides/gs/securing-web/

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import esm.dnd.DnDDAM2EnriqueSergioMangel.modelo.FichaPersonaje;
import esm.dnd.DnDDAM2EnriqueSergioMangel.modelo.Usuario;
import esm.dnd.DnDDAM2EnriqueSergioMangel.servicio.IFichaPersonajeServicio;
import esm.dnd.DnDDAM2EnriqueSergioMangel.servicio.IUsuarioServicio;

@CrossOrigin(origins = {"*"},methods = {RequestMethod.DELETE,RequestMethod.GET,RequestMethod.POST,RequestMethod.PUT,RequestMethod.OPTIONS})
@RestController
@RequestMapping("/api/dndtools/usuarios")
public class UsuarioController {

	@Autowired
    private IUsuarioServicio usuarioServicio;
	
	@Autowired
    private IFichaPersonajeServicio fichaPersonajeServicio;
    
    @GetMapping("/dametodos")
    public ResponseEntity<List<Usuario>> obtenerTodosLosUsuarios()
    {
        ResponseEntity<List<Usuario>> res = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        
        List<Usuario> allUsuarios = usuarioServicio.findAllUsuarios();
        
        res = new ResponseEntity<List<Usuario>>(allUsuarios, HttpStatus.OK);
        
        return res;
    }

	@PostMapping("/insertarUsuario")
    public ResponseEntity<Usuario> insertarUsuario(@RequestBody Usuario usuario)
    {
    	ResponseEntity<Usuario> res = new ResponseEntity<>(new Usuario(),HttpStatus.BAD_REQUEST);
    	    	
    	try {
			usuario.setIdUser(ObjectId.get());
			usuario.setIdUserString(usuario.getIdUser().toString());
			usuarioServicio.insertarUsuario(usuario);
			Usuario u=usuarioServicio.findUsuarioById(usuario.getIdUserString()).get();
			res = new ResponseEntity<Usuario>(u, HttpStatus.OK);
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
    	
    	return res;
    }

	@DeleteMapping("/deleteById")
	public ResponseEntity<Usuario> deleteById(@RequestParam String id)
	{
		Usuario u;
		
		ResponseEntity<Usuario> res = new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
		
		if(usuarioServicio.existeUsuario(id))
		{
			u = usuarioServicio.findUsuarioById(id).get();
			usuarioServicio.eliminarUsuario(id);
			res = new ResponseEntity<Usuario>(u,HttpStatus.ACCEPTED);
		}
		
		return res;
		
	}

	@PutMapping("/update")
    public ResponseEntity<String> actualizarUsuario(@RequestBody Usuario usuario){
        ResponseEntity<String> res = new ResponseEntity<String>("Fallo al actualizar el usuario",HttpStatus.OK);
        if(usuarioServicio.existeUsuario(usuario.getIdUserString())){
            try {
                usuarioServicio.editarUsuario(usuario);
                res = new ResponseEntity<>("Exito al actualizar el usuario",HttpStatus.OK);
                return res;
            } catch (Exception e) {
                return res;
            }
        }else{
            return res;
        }
    }
	
	@PostMapping("/trylogin")
    public ResponseEntity<Usuario> loguearse(@RequestBody Usuario u)
    {
        ResponseEntity<Usuario> res = new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        Usuario user;
        
        if (usuarioServicio.existsByNickname(u.getNickname())) {
        	
        	user = usuarioServicio.findUsuarioByNickname(u.getNickname()).get();
        	
        	if (user.getContrasenia().equals(u.getContrasenia()))
        	{
        		res = new ResponseEntity<Usuario>(user, HttpStatus.OK);
        	}
        			
        }
        
        return res;
    }
	
	@PostMapping("/tryloginParams")
    public ResponseEntity<Usuario> loguearsePorParametros(@RequestParam String nickname, @RequestParam String contrasenia)
    {
        ResponseEntity<Usuario> res = new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        
        if (usuarioServicio.existsByNickname(nickname)) {
        	
        	Usuario user = usuarioServicio.findUsuarioByNickname(nickname).get();
        	
        	if (user.getContrasenia().equals(contrasenia))
        	{
        		res = new ResponseEntity<Usuario>(user, HttpStatus.OK);
        	}
        			
        }
        
        return res;
    }
	
	@DeleteMapping("/clear")
	public ResponseEntity<Iterable<Usuario>> deleteAll()
	{
		
		ResponseEntity<Iterable<Usuario>> res = new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
		
		Iterable<Usuario> users = usuarioServicio.eliminarTodos();
		
		//Elimino tambien los de dentro de las fichas
		
		List<FichaPersonaje> fichas =  (List<FichaPersonaje>) fichaPersonajeServicio.deleteAllFichas();
		fichas.stream().forEach(f -> f.setUsuario(null));
		
		fichaPersonajeServicio.addAllFichasPersonaje(fichas);
		
		res = new ResponseEntity<Iterable<Usuario>>(users, HttpStatus.ACCEPTED);
		
		return res;
		
	}
    
    @PostMapping("/insertarPorParametro")
    public ResponseEntity<String> insertarPorParametros(@RequestParam String nombre, @RequestParam String apellidos, @RequestParam String contrasenia, @RequestParam String nickname, @RequestParam String biografia, @RequestParam String email, @RequestParam String fechaNac, @RequestParam String urlImage, /*@RequestParam boolean activo, */@RequestParam String pais)
    {
    	ResponseEntity<String> res = new ResponseEntity<>("Error insertando usuario",HttpStatus.BAD_REQUEST);
    	
    	try {

			Usuario u ;

			u = Usuario.builder()
					.idUser(ObjectId.get())
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
					.idUser(ObjectId.get())	//	Genero un id nuevo
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

	@GetMapping("/getAll")
    public ResponseEntity<List<Usuario>> getAll(){
        
        ResponseEntity<List<Usuario>> res = new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        List<Usuario> usuarios= usuarioServicio.getAll();

        if(usuarios.isEmpty()){
            return res;
        }else{
            return new ResponseEntity<List<Usuario>>(usuarios,HttpStatus.OK);
        }
    }

    
}
