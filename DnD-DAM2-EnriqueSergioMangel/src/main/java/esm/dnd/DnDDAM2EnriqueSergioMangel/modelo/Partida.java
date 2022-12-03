package esm.dnd.DnDDAM2EnriqueSergioMangel.modelo;

import java.util.List;
import java.util.UUID;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.stereotype.Component;

import com.mongodb.lang.Nullable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)

@Component
@Document
public class Partida {
    
    @Id
    @EqualsAndHashCode.Include
    private ObjectId idPartida;
    @Nullable
    private String codigoPartida;
    @Nullable
    private String nombre;
    @Nullable
    private Usuario creador;
    @Nullable
    private List<Usuario> usuariosPartida;
    @Nullable
    private List<Equipamiento> equipoPartida;
    @Nullable
    private List<Npc> npcsPartida;
    @Nullable
    private List<Monstruo> monstruosPartida;
    @Nullable
    private List<FichaPersonaje> fichasPartida;

    public Partida(Usuario creador){
        this.creador=creador;
        //se crea un codigo de partida mediante un UUID aleatorio
        this.codigoPartida=UUID.randomUUID().toString();
        this.usuariosPartida=null;
        this.equipoPartida=null;
        this.npcsPartida=null;
        this.monstruosPartida=null;
        this.fichasPartida=null;
    }
}
