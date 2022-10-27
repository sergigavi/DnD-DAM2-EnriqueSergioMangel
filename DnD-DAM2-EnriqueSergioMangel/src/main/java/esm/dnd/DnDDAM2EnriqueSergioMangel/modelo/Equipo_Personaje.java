package esm.dnd.DnDDAM2EnriqueSergioMangel.modelo;

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

public class Equipo_Personaje {

	@NonNull
	@Id
	@EqualsAndHashCode.Include
	private String idEquipo;
	
	//onetoone
	private String idPersonaje;
	
	//manytoone
	private String idEquipamiento;
	
	private int cantidad;
	
	private boolean equipado;
}