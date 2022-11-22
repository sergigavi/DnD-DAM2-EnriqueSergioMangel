package esm.dnd.DnDDAM2EnriqueSergioMangel.RESTControllers;

import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import esm.dnd.DnDDAM2EnriqueSergioMangel.modelo.Equipamiento;
import esm.dnd.DnDDAM2EnriqueSergioMangel.servicio.EquipamientoServicio;
import io.swagger.v3.oas.annotations.parameters.RequestBody;

@CrossOrigin(originPatterns = {"*"},methods = {RequestMethod.DELETE,RequestMethod.GET,RequestMethod.OPTIONS,RequestMethod.POST,RequestMethod.PUT})    //Con esta anotacion se salta el protocolo para poder acceder a la API desde el fetch de javascript etcetc
@RestController
@RequestMapping("/api/dndtools/equipamiento")
public class EquipamientoController {
    
    @Autowired private EquipamientoServicio equipamientoServicio;

    @GetMapping("/getAll")
    public ResponseEntity<List<Equipamiento>> getAll(){
        
        ResponseEntity<List<Equipamiento>> res = new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        List<Equipamiento> equipamientos= equipamientoServicio.getAll();

        if(equipamientos.isEmpty()){
            return res;
        }else{
            return new ResponseEntity<List<Equipamiento>>(equipamientos,HttpStatus.OK);
        }
    }

    @PostMapping("/add")
    public ResponseEntity<String> addEquipamiento(@RequestBody Equipamiento equipamiento){

        ResponseEntity<String> res = new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        equipamiento.setIdEquipo(ObjectId.get());
        if(equipamientoServicio.insertarEquipamiento(equipamiento)){
            return new ResponseEntity<String>("Exito",HttpStatus.OK);
        }else{
            return res;
        }
    }

    @DeleteMapping("/delete")
    public ResponseEntity<String> deleteEquipamiento(@RequestBody ObjectId id){

        ResponseEntity<String> res = new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        if(equipamientoServicio.eliminarEquipamientoTemp(id)){
            return new ResponseEntity<String>("Exito",HttpStatus.OK);
        }else{
            return res;
        }
    }
}
