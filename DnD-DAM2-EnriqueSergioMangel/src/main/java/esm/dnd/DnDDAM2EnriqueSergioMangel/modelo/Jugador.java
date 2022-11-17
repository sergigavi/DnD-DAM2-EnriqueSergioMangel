package esm.dnd.DnDDAM2EnriqueSergioMangel.modelo;

import java.util.UUID;

import org.springframework.data.annotation.Id;
import org.springframework.stereotype.Component;

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

@Component
public class Jugador {
    
    @Id
    @EqualsAndHashCode.Include
    private UUID idJugador;

    private UUID idUsuario;

    private UUID idFicha;

    private String notas;
}
