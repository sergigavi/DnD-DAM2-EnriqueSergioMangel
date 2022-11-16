package esm.dnd.DnDDAM2EnriqueSergioMangel.RESTControllers;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import esm.dnd.DnDDAM2EnriqueSergioMangel.modelo.Administrador;
import esm.dnd.DnDDAM2EnriqueSergioMangel.servicio.IAdministradorServicio;

@CrossOrigin
@RestController
@RequestMapping("/api/dndtools/admins")
public class AdministradorController {
	
	@Autowired
	private IAdministradorServicio administradorServicio;
	
	@GetMapping("/getAll")
    public ResponseEntity<Iterable<Administrador>> obtenerTodosLosAdministradores()
    {
        ResponseEntity<Iterable<Administrador>> res = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        
        Iterable<Administrador> allAdmins = administradorServicio.findAllAdmins();
        
        res = new ResponseEntity<Iterable<Administrador>>(allAdmins, HttpStatus.OK);
        
        return res;
    }
	
	@PostMapping("/addAdmin")
    public ResponseEntity<String> addAdmin(@RequestBody Administrador admin)
    {
        ResponseEntity<String> res = new ResponseEntity<>("Error insertando el admin",HttpStatus.BAD_REQUEST);
        
        admin.setIdAdmin(UUID.randomUUID());
        
        if (administradorServicio.insertarAdministrador(admin)) {
        	res = new ResponseEntity<String>("Administrador insertado correctamente", HttpStatus.OK);
        }
        
        return res;
    }
	
	@DeleteMapping("/deleteById")
    public ResponseEntity<Administrador> deleteAdminById(@RequestParam UUID id)
    {
		Administrador admin;
		
        ResponseEntity<Administrador> res = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        
        if (administradorServicio.existeAdministrador(id)) {
        
        	admin = administradorServicio.findAdminByID(id).get();
        	administradorServicio.eliminarAdministrador(id);
        	res = new ResponseEntity<Administrador>(admin, HttpStatus.OK);
        }        
        
        return res;
    }

}