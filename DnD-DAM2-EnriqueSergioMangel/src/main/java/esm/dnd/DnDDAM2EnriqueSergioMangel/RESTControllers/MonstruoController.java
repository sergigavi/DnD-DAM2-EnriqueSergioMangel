package esm.dnd.DnDDAM2EnriqueSergioMangel.RESTControllers;

import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import esm.dnd.DnDDAM2EnriqueSergioMangel.modelo.Monstruo;
import esm.dnd.DnDDAM2EnriqueSergioMangel.servicio.MonstruoServicio;
import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
@RequestMapping("/api/dndtools/monstruo")
public class MonstruoController {
    @Autowired private MonstruoServicio monstruoServicio;

    @GetMapping("/getAll")
    public ResponseEntity<List<Monstruo>> getAll(){

        ResponseEntity<List<Monstruo>> res = new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        try {
            List<Monstruo> monstruos = monstruoServicio.findAllMonstruo();
            res = new ResponseEntity<>(monstruos, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return res;
    }
    
    @PostMapping("/addMonstruo")
    public ResponseEntity<String> addMonstruo(@RequestBody Monstruo monstruo){
        ResponseEntity<String> res = new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        try {
            if(monstruoServicio.insertarMonstruo(monstruo)){
                res = new ResponseEntity<>("Exito al crear el monstruo",HttpStatus.CREATED);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return res;
    }

    @DeleteMapping("/deleteMonstruo")
    public ResponseEntity<String> deleteMonstruo(@RequestBody ObjectId id){
        ResponseEntity<String> res = new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        try {
            if(monstruoServicio.deleteMonstruo(id)){
                res = new ResponseEntity<>("Exito al borrar monstruo",HttpStatus.OK);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return res;
    }

    @DeleteMapping("/deleteAll")
    public ResponseEntity<String> deleteAll(){
        ResponseEntity<String> res = new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        try {
            //Nos devuelve una lista de monstruos antes de eliminarla por si queremos hacer algo con ella
            List<Monstruo> monstruos= monstruoServicio.deleteAll();
            res = new ResponseEntity<>("Exito al eliminar todos los monstruos",HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return res;
    }

    @GetMapping("/findMonstruo")
    public ResponseEntity<Monstruo> findMonstruo(@RequestBody ObjectId id){
        ResponseEntity<Monstruo> res = new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        try {
            if(monstruoServicio.findMonstruoById(id).isPresent()){
                Monstruo m = monstruoServicio.findMonstruoById(id).get();
                res = new ResponseEntity<>(m,HttpStatus.OK);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return res;
    }

    @PutMapping("/updateMonstruo")
    public ResponseEntity<Monstruo> updateMonstruo(@RequestBody Monstruo monstruo){
        ResponseEntity<Monstruo> res = new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        try {
            if(monstruoServicio.editarMonstruo(monstruo)){
                //Esta línea es algo redundante, pero es para comprobar que nos devuelve el monstruo que acabamos de editar
                Monstruo m=monstruoServicio.findMonstruoById(monstruo.getIdMonstruo()).get();
                res = new ResponseEntity<>(m,HttpStatus.OK);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return res;
    }

}
