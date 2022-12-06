package esm.dnd.DnDDAM2EnriqueSergioMangel.modelo;

import java.time.LocalDate;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;
import org.springframework.stereotype.Component;

import com.mongodb.lang.Nullable;

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
	
	@MongoId
	private ObjectId idUser;

	@Nullable
	@EqualsAndHashCode.Include
	private String idUserString;
	
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
