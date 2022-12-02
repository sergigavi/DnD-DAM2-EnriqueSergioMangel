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

import esm.dnd.DnDDAM2EnriqueSergioMangel.modelo.Npc;
import esm.dnd.DnDDAM2EnriqueSergioMangel.servicio.INpcServicio;
import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
@RequestMapping("/api/dndtools/npc")
public class NpcController {
    
    @Autowired private INpcServicio npcServicio;

    @GetMapping("/getAll")
    public ResponseEntity<List<Npc>> getAll(){
        ResponseEntity<List<Npc>> response = new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        List<Npc> npcs = npcServicio.findAllNpc();

        if(npcs.isEmpty()){
            return response;
        }else
            return response= new  ResponseEntity<List<Npc>>(npcs,HttpStatus.OK);
    }

    @PostMapping("/addNpc")
    public ResponseEntity<String> addNpc(@RequestBody Npc npc){

        ResponseEntity<String> res = new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        try {
            npc.setIdNpc(ObjectId.get());
            npcServicio.insertarNpc(npc);
            res = new ResponseEntity<>("Exito al insertar el npc",HttpStatus.CREATED);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return res;
    }

    @GetMapping("/findNpc")
    public ResponseEntity<Npc> findNpc(@RequestBody ObjectId id){
        ResponseEntity<Npc> res = new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        try {
            if(npcServicio.findNpcById(id).isPresent()){
                Npc npc = npcServicio.findNpcById(id).get();
                res = new ResponseEntity<>(npc,HttpStatus.OK);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return res;
    }

    @DeleteMapping("/deleteNpc")
    public ResponseEntity<String> deleteNpc(@RequestBody ObjectId id){
        ResponseEntity<String> res = new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        try {
            if(npcServicio.deleteNpc(id)){
                res = new ResponseEntity<>("Exito al eliminar el npc",HttpStatus.OK);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return res;
    }

    @DeleteMapping("/deleteAllNpc")
    public ResponseEntity<String> deleteAllNpc(){

        ResponseEntity<String> res = new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        try {
            //Nos devuelve la lista de npcs antes de borrarla por si queremos hacer algo con ella
            List<Npc> npcs=npcServicio.deleteAll();
            res = new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return res;
    }

    @PutMapping("/updateNpc")
    public ResponseEntity<Npc> updateNpc(@RequestBody Npc npc){
        ResponseEntity<Npc> res = new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        try {
            if(npcServicio.editarNpc(npc)){
                //Esta linea es reduntante pero es para comprobar que nos devuelve el objeto correspondiente
                Npc n=npcServicio.findNpcById(npc.getIdNpc()).get();
                res = new ResponseEntity<>(n,HttpStatus.OK);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return res;
    }
}
