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

public class Equipamiento {

	@Id
	@NonNull
	@EqualsAndHashCode.Include
	private String idEquipo;
	
	private String nombre;
	
	private TipoEquipo tipo;
	
	private CatEquipo categoria;
	
	private PropiedadEquipo propiedad;
	
	private String modificador;
	
	private double danio;
	
	private double alcance;
	
	private double precio;
	
	private double peso;
		
	private String descripcion;
	
	
}
