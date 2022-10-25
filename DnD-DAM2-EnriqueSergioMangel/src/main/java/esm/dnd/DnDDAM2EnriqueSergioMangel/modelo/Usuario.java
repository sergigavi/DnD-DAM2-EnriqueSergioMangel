package esm.dnd.DnDDAM2EnriqueSergioMangel.modelo;

import java.time.LocalDate;

//import javax.persistence.Entity;
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
public class Usuario {
	
	@Id
	@NonNull
	@EqualsAndHashCode.Include
	private String idUser;
	
	private String nombre;
	
	private String apellidos;
	
	private String contrasenia;
	
	private String nickname;
	
	private String biografia;
	
	private String email;
	
	private LocalDate fechaNacimiento;
	
	private String urlImage;
	
	private boolean activo;
	
	private String pais;
	
	

}
