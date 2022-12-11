package esm.dnd.DnDDAM2EnriqueSergioMangel.RESTControllers;

import java.util.ArrayList;
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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import esm.dnd.DnDDAM2EnriqueSergioMangel.modelo.Partida;
import esm.dnd.DnDDAM2EnriqueSergioMangel.modelo.Usuario;
import esm.dnd.DnDDAM2EnriqueSergioMangel.servicio.PartidaServicio;
import esm.dnd.DnDDAM2EnriqueSergioMangel.servicio.UsuarioServicio;
import io.swagger.v3.oas.annotations.parameters.RequestBody;

@CrossOrigin(origins = {"*"},methods = {RequestMethod.DELETE,RequestMethod.POST,RequestMethod.GET,RequestMethod.PUT,RequestMethod.OPTIONS})
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
    public ResponseEntity<Partida> findPartidaById(@RequestBody String id){
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
            if(partidaServicio.existePartida(partida.getIdStringPartida())){
                Partida p= partidaServicio.findPartidaById(partida.getIdStringPartida()).get();
                res = new ResponseEntity<>(p,HttpStatus.OK);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return res;
    }

    @DeleteMapping("/deletePartidaById")
    public ResponseEntity<Partida> deletePartidaById(@RequestBody String id){
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
            if(partidaServicio.existePartida(partida.getIdStringPartida())){
                Partida p = partidaServicio.findPartidaById(partida.getIdStringPartida()).get();
                partidaServicio.deletePartidaById(partida.getIdStringPartida());
                res = new ResponseEntity<>(p,HttpStatus.OK);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return res;
    }

    @PostMapping("/add")
    public ResponseEntity<String> addPartida(@RequestBody Partida partida){
    	
        ResponseEntity<String> res = new ResponseEntity<>("Error insertando la partida",HttpStatus.BAD_REQUEST);

        System.out.println(partida);
        
        ObjectId id = ObjectId.get();
        ObjectId idPartida = ObjectId.get();
        String idString = id.toString();
        String idStringPartida = idPartida.toString();
        
        Partida pa= Partida.builder()
            .idPartida(id)
            .idStringPartida(idString)
            .codigoPartida(idStringPartida)
            .nombre(partida.getNombre())
            .creador(partida.getCreador())
            .usuariosPartida(null)
            .equipoPartida(null)
            .fichasPartida(null)
            .build();
        
        //pa.setNombre("partida 1");
        //pa.setCreador(usuarioServicio.findUsuarioById("639634259669ca5b48803fc2").get());


        try {
            partidaServicio.addPartida(pa);
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
    public ResponseEntity<List<Partida>> findPartidasByIdUser(@RequestBody String id){
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
            if(partidaServicio.existePartida(partida.getIdStringPartida())){
                partidaServicio.addPartida(partida);
                //Esta linea es redundante pero se usa para verificar que la partida ha sido modificada
                Partida p = partidaServicio.findPartidaById(partida.getIdStringPartida()).get();
                res = new ResponseEntity<>(p,HttpStatus.OK);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return res;
    }
}
