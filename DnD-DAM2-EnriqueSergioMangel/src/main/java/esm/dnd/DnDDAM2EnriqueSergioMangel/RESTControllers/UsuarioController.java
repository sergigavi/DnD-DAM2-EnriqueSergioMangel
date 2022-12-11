package esm.dnd.DnDDAM2EnriqueSergioMangel.RESTControllers;

//https://spring.io/guides/gs/securing-web/

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
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
    
	@GetMapping("/findById/{id}")
	public ResponseEntity<Usuario> findUsuarioById(@PathVariable String id){

		ResponseEntity<Usuario> res = new ResponseEntity<>(HttpStatus.BAD_REQUEST);

		try {
			if(usuarioServicio.existeUsuario(id)){
				Usuario u = usuarioServicio.findUsuarioById(id).get();
				res = new ResponseEntity<Usuario>(u,HttpStatus.OK);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;

	}
	
	@GetMapping("/findByIdString/{id}")
	public ResponseEntity<Usuario> findUsuarioByIdString(@PathVariable String id){

		ResponseEntity<Usuario> res = new ResponseEntity<>(HttpStatus.BAD_REQUEST);

		try {
			if(usuarioServicio.existeUsuario(id)){
				Usuario u = usuarioServicio.findUsuarioByIdString(id).get();
				res = new ResponseEntity<Usuario>(u,HttpStatus.OK);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;

	}
	
	@PutMapping("/cambiarContraById/{id}")
	public ResponseEntity<Boolean> cambiarContraById(@PathVariable String id, @RequestBody String contra){

		ResponseEntity<Boolean> res = new ResponseEntity<>(false,HttpStatus.BAD_REQUEST);

		try {
			if(usuarioServicio.existeUsuario(id)){
				usuarioServicio.cambiarContrasenia(id, contra);
				res = new ResponseEntity<Boolean>(true,HttpStatus.OK);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;

	}
	
	@PutMapping("/editarUserById/{id}")
	public ResponseEntity<Boolean> editarUserById(@PathVariable String id, @RequestBody Usuario usuario){

		ResponseEntity<Boolean> res = new ResponseEntity<>(false,HttpStatus.BAD_REQUEST);

		try {
			if(usuarioServicio.existeUsuario(id)){
				
				Usuario u = usuarioServicio.findUsuarioByIdString(id).get();
				u.setNombre(usuario.getNombre());
				u.setApellidos(usuario.getApellidos());
				u.setNickname(usuario.getNickname());
				u.setActivo(usuario.isActivo());
				u.setPais(usuario.getPais());
				u.setFechaNacimiento(usuario.getFechaNacimiento());
				u.setBiografia(usuario.getBiografia());
				u.setUrlImage(usuario.getUrlImage());
				
				usuarioServicio.guardarUserCompletoEnDB(u);
				
				res = new ResponseEntity<Boolean>(true,HttpStatus.OK);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;

	}
	
	@GetMapping("/getNombreById/{id}")
	public ResponseEntity<String> findNombreUsuarioByIdString(@PathVariable String id){

		ResponseEntity<String> res = new ResponseEntity<>(HttpStatus.BAD_REQUEST);

		try {
			if(usuarioServicio.existeUsuario(id)){
				Usuario u = usuarioServicio.findUsuarioByIdString(id).get();
				res = new ResponseEntity<String>(u.getNombre(),HttpStatus.OK);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;

	}


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
    	
    	if (!usuarioServicio.existsByEmail(usuario.getEmail()))
    	{
        	try {
    			usuario.setIdUser(ObjectId.get());
    			usuario.setIdUserString(usuario.getIdUser().toString());
    			usuarioServicio.insertarUsuario(usuario);
    			Usuario u=usuarioServicio.findUsuarioById(usuario.getIdUserString()).get();
    			res = new ResponseEntity<Usuario>(u, HttpStatus.OK);
    			
    			
    		} catch (Exception e) {
    			e.printStackTrace();
    		}
        	
    	}
    	return res;
    	
    }

	@PostMapping("/add")
    public ResponseEntity<String> addUsuario(@RequestBody Usuario usuario){

        ResponseEntity<String> res = new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        System.out.println(usuario.toString());

        ObjectId id = ObjectId.get();
        String idString = id.toString();

        Usuario us= Usuario.builder()
            .idUser(id)
            .idUserString(idString)
            .nombre(usuario.getNombre())
			.apellidos(usuario.getApellidos())
			.contrasenia(usuario.getContrasenia())
			.nickname(usuario.getNickname())
			.biografia(usuario.getBiografia())
			.email(usuario.getEmail())
			.fechaNacimiento(usuario.getFechaNacimiento())
			.urlImage("")
			.activo(true)
			.pais(usuario.getPais())
            .build();

            try {
                usuarioServicio.insertarUsuario(us);
                res = new ResponseEntity<String>("Exito",HttpStatus.OK);
            } catch (HttpMessageNotReadableException e) {
                res = new ResponseEntity<String>("Fallo al leer",HttpStatus.BAD_REQUEST);
                e.printStackTrace();
            } catch(Exception e){
                res = new ResponseEntity<String>("Fallo al leer",HttpStatus.BAD_REQUEST);
                e.printStackTrace();
            }

        return res;
    }

	@DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteUsuarioById(@PathVariable String id){

        ResponseEntity<String> res = new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        if(usuarioServicio.eliminarUsuario(id)){
        	List<FichaPersonaje> fichasAEliminar = fichaPersonajeServicio.findAllFichasPersonaje().stream().filter(f -> f.getUsuario().getIdUserString().equals(id)).collect(Collectors.toList());
        	fichasAEliminar.forEach(f -> fichaPersonajeServicio.deleteFichaPersonajeById(f.getIdFichaPersonajeString()));
            return new ResponseEntity<String>("Exito",HttpStatus.OK);
        }else{
            return res;
        }
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
	
	@GetMapping("/trylogin/{email}/{contrasenia}")
    public ResponseEntity<Usuario> loguearse(@PathVariable String email,@PathVariable String contrasenia)
    {
        ResponseEntity<Usuario> res = new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        Usuario user;
        
        if (usuarioServicio.existsByEmail(email)) {
        	
        	user = usuarioServicio.findByEmail(email).get();
        	
        	if (user.getContrasenia().equals(contrasenia))
        	{
        		res = new ResponseEntity<Usuario>(user, HttpStatus.OK);
        	}
        			
        }
        
        return res;
    }

	@GetMapping("existsByEmail/{email}")
	public ResponseEntity<Boolean> existsByEmail(@PathVariable String email){
		ResponseEntity<Boolean> res = new ResponseEntity<>(HttpStatus.BAD_REQUEST);

		try {
			if(usuarioServicio.existsByEmail(email)){
				res = new ResponseEntity<Boolean>(true,HttpStatus.OK);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}
	/*
	@DeleteMapping("/clear")
	public ResponseEntity<Iterable<Usuario>> deleteAll()
	{
		
		ResponseEntity<Iterable<Usuario>> res = new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
		
		try {
			Iterable<Usuario> users = usuarioServicio.eliminarTodos();
		
			//Elimino tambien los de dentro de las fichas
			
			List<FichaPersonaje> fichas= fichaPersonajeServicio.findAllFichasPersonaje();
			
	
			res = new ResponseEntity<Iterable<Usuario>>(users, HttpStatus.ACCEPTED);
		} catch (Exception e) {
			// TODO: handle exception
		}

		
		return res;
		
	}
     */
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

	@GetMapping("/cantidad")
    public ResponseEntity<Long> getCantidad(){
        
        ResponseEntity<Long> res = new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        long cantidad= usuarioServicio.getCantidad();

        if(cantidad==0){
            return res;
        }else{
            return new ResponseEntity<Long>(cantidad,HttpStatus.OK);
        }
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
