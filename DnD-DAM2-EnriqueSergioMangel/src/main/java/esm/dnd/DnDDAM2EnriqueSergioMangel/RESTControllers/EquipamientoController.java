package esm.dnd.DnDDAM2EnriqueSergioMangel.RESTControllers;

import java.util.List;

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
import org.springframework.web.bind.annotation.RestController;

import esm.dnd.DnDDAM2EnriqueSergioMangel.modelo.Equipamiento;
import esm.dnd.DnDDAM2EnriqueSergioMangel.servicio.EquipamientoServicio;

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

        System.out.println(equipamiento.toString());

        ObjectId id = ObjectId.get();
        String idString = id.toString();

        Equipamiento eq= Equipamiento.builder()
            .alcance(equipamiento.getAlcance())
            .idEquipo(id)
            .idString(idString)
            .categoria(equipamiento.getCategoria())
            .danio(equipamiento.getDanio())
            .descripcion(equipamiento.getDescripcion())
            .modificador(equipamiento.getModificador())
            .nombre(equipamiento.getNombre())
            .peso(equipamiento.getPeso())
            .precio(equipamiento.getPrecio())
            .propiedad(equipamiento.getPropiedad())
            .tipo(equipamiento.getTipo())
            .build();

            try {
                equipamientoServicio.insertarEquipamiento(eq);
                res = new ResponseEntity<String>("Exito",HttpStatus.OK);
            } catch (HttpMessageNotReadableException e) {
                res = new ResponseEntity<String>("Fallo al leer el tipo, categoria o propiedad",HttpStatus.BAD_REQUEST);
                e.printStackTrace();
            }catch(Exception e){
                res = new ResponseEntity<String>("Fallo al leer el tipo, categoria o propiedad",HttpStatus.BAD_REQUEST);
                e.printStackTrace();
            }

        return res;
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteEquipamientoById(@PathVariable String id){

        ResponseEntity<String> res = new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        if(equipamientoServicio.eliminarEquipamiento(id)){
            return new ResponseEntity<String>("Exito",HttpStatus.OK);
        }else{
            return res;
        }
    }

    @PutMapping("/update")
    public ResponseEntity<String> updateEquipamiento(@RequestBody Equipamiento equipamiento){
        ResponseEntity<String> res = new ResponseEntity<String>("Fallo al actualizar la ficha",HttpStatus.OK);
        if(equipamientoServicio.existeEquipamiento(equipamiento.getIdString())){
            try {
                equipamientoServicio.editarEquipo(equipamiento);
                res = new ResponseEntity<>("Exito al actualizar la ficha",HttpStatus.OK);
                return res;
            } catch (Exception e) {
                return res;
            }
        }else{
            return res;
        }
    }

    @DeleteMapping("/deleteAll")
    public ResponseEntity<Boolean> deleteAll(){
        ResponseEntity<Boolean> res = new ResponseEntity<>(false,HttpStatus.BAD_REQUEST);
        try {
            equipamientoServicio.deleteAll();
            res = new ResponseEntity<Boolean>(true, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return res;
        }
        return res;
    }
}
