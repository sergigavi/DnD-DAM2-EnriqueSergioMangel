package esm.dnd.DnDDAM2EnriqueSergioMangel.modelo;

import org.springframework.data.annotation.Id;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode(onlyExplicitlyIncluded = true)

public class Jugador {
    
    @Id
    @EqualsAndHashCode.Include
    private String idJugador;

    private String idUsuario;

    private String idFicha;

    private String notas;
}