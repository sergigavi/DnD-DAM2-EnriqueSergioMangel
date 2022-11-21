package esm.dnd.DnDDAM2EnriqueSergioMangel.modelo;

import java.time.LocalDate;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Builder

@Document
@Component
public class Usuario {
	
	@Id
	@EqualsAndHashCode.Include
	private ObjectId idUser;
	
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
