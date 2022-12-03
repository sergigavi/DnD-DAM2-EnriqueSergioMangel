package esm.dnd.DnDDAM2EnriqueSergioMangel.RESTControllers;

import java.util.ArrayList;
import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import esm.dnd.DnDDAM2EnriqueSergioMangel.modelo.Partida;
import esm.dnd.DnDDAM2EnriqueSergioMangel.modelo.Usuario;
import esm.dnd.DnDDAM2EnriqueSergioMangel.servicio.PartidaServicio;
import esm.dnd.DnDDAM2EnriqueSergioMangel.servicio.UsuarioServicio;
import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
@RequestMapping("/api/dndtools/partida")
public class PartidaController {
    
    @Autowired private PartidaServicio partidaServicio;
    @Autowired private UsuarioServicio usuarioServicio;

    @GetMapping("/getAll")
    public ResponseEntity<List<Partida>> getAll(){
        ResponseEntity<List<Partida>> res = new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        try {
            List<Partida> partidas=partidaServicio.findAll();
            res = new ResponseEntity<>(partidas,HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return res;
    }

    @GetMapping("/findPartidaById")
    public ResponseEntity<Partida> findPartidaById(@RequestBody ObjectId id){
        ResponseEntity<Partida> res = new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        try {
            if(partidaServicio.existePartida(id)){
                Partida p= partidaServicio.findPartidaById(id).get();
                res = new ResponseEntity<>(p,HttpStatus.OK);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return res;
    }

    @GetMapping("/findPartida")
    public ResponseEntity<Partida> findPartida(@RequestBody Partida partida){
        ResponseEntity<Partida> res = new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        try {
            if(partidaServicio.existePartida(partida.getIdPartida())){
                Partida p= partidaServicio.findPartidaById(partida.getIdPartida()).get();
                res = new ResponseEntity<>(p,HttpStatus.OK);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return res;
    }

    @DeleteMapping("/deletePartidaById")
    public ResponseEntity<Partida> deletePartidaById(@RequestBody ObjectId id){
        ResponseEntity<Partida> res = new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        try {
            if(partidaServicio.existePartida(id)){
                Partida p = partidaServicio.findPartidaById(id).get();
                partidaServicio.deletePartidaById(id);
                res = new ResponseEntity<>(p,HttpStatus.OK);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return res;
    }

    @DeleteMapping("/deletePartida")
    public ResponseEntity<Partida> deletePartida(@RequestBody Partida partida){
        ResponseEntity<Partida> res = new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        try {
            if(partidaServicio.existePartida(partida.getIdPartida())){
                Partida p = partidaServicio.findPartidaById(partida.getIdPartida()).get();
                partidaServicio.deletePartidaById(partida.getIdPartida());
                res = new ResponseEntity<>(p,HttpStatus.OK);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return res;
    }

    @PostMapping("/addPartida")
    public ResponseEntity<Partida> addPartida(@RequestBody Partida partida){
        ResponseEntity<Partida> res = new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        try {
            if(!partidaServicio.existePartida(partida.getIdPartida())){
                partidaServicio.addPartida(partida);
                res = new ResponseEntity<>(partidaServicio.findPartidaById(partida.getIdPartida()).get(),HttpStatus.OK);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return res;
    }

    @DeleteMapping("/deleteAll")
    public ResponseEntity<List<Partida>> deleteAll(){
        ResponseEntity<List<Partida>> res = new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        try {
            List<Partida> partidas= partidaServicio.findAll();
            partidaServicio.deleteAll();
            res = new ResponseEntity<>(partidas,HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return res;
    }

    @GetMapping("/findPartidasByIdUser")
    public ResponseEntity<List<Partida>> findPartidasByIdUser(@RequestBody ObjectId id){
        ResponseEntity<List<Partida>> res = new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        try {
            Usuario usuario = usuarioServicio.findUsuarioById(id).get();
            List<Partida> partidas=partidaServicio.findAll();
            List<Partida> partidasCreador = new ArrayList<>();
            List<Partida> partidasJugador = new ArrayList<>();
            List<Partida> partidasUsuario = new ArrayList<>();

            if(!partidas.isEmpty()){

                partidas.stream()
                .filter(p->p.getCreador().equals(usuario))
                .forEach(p->{
                    partidasCreador.add(p);
                });

                partidas.stream()
                    .filter(p->p.getUsuariosPartida().contains(usuario))
                    .forEach(p->{
                        partidasJugador.add(p);
                    });

                partidasUsuario.addAll(partidasCreador);
                partidasUsuario.addAll(partidasJugador);

            }

            if(!partidasUsuario.isEmpty()){
                res = new ResponseEntity<>(partidasUsuario,HttpStatus.OK);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return res;
    }

    public ResponseEntity<Partida> editarPartida(@RequestBody Partida partida){
        ResponseEntity<Partida> res = new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        try {
            if(partidaServicio.existePartida(partida.getIdPartida())){
                partidaServicio.addPartida(partida);
                //Esta linea es redundante pero se usa para verificar que la partida ha sido modificada
                Partida p = partidaServicio.findPartidaById(partida.getIdPartida()).get();
                res = new ResponseEntity<>(p,HttpStatus.OK);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return res;
    }
}
