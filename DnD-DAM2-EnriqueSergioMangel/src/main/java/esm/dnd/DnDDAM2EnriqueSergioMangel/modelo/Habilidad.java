package esm.dnd.DnDDAM2EnriqueSergioMangel.modelo;

import java.time.LocalDate;

import org.springframework.data.annotation.Id;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Builder

@Entity
public class Habilidad {
	
	@Id
	@NonNull
	@EqualsAndHashCode.Include
	private String idHabilidad;
	
	private String idFicha;
	
	private String nombre;
	
	private String competencia;

}
