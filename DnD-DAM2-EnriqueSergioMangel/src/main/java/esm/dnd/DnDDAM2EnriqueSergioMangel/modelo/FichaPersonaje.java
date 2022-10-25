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

//@Entity
public class FichaPersonaje {
	
	@Id
	@NonNull
	@EqualsAndHashCode.Include
	private String idFichaPersonaje;
	
	private String idUsuario;
	
	private String nombre;
	
	private Clase clase;
	
	private Raza raza;
	
	private Alineamiento alineamiento;
	
	private int nivel;
	
	private String transfondo;
	
	private String nombreJugador;
	
	private String ca;
	
	private int velocidad;
	
	private int puntosVidaMax;
	
	private int puntosVidaActuales;
	
	private Iterable<String> rasgosPersonalidades;
	
	private Iterable<String> ideales;
	
	private Iterable<String> vinculos;
	
	private Iterable<String> defectos;
	
	private Iterable<String> rasgosAtt;
	
	private Iterable<String> otrasComp;
	
	private Iterable<String> apariencia;
	
	private String historiaPersonal;
	
	private Iterable<String> rasgos;
	
	private String notasAdd;
	
	
	
	
}



