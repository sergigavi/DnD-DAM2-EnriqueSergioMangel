package esm.dnd.DnDDAM2EnriqueSergioMangel.modelo;



import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;
import org.springframework.stereotype.Component;

import com.mongodb.lang.Nullable;

/*import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;*/

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
public class Administrador {
	
	@MongoId
	private ObjectId idAdmin;

	@EqualsAndHashCode.Include
	@Nullable
	private String idAdminString;
	
	private String nombre;
	
	private String email;
	
	private String contrasenia;
	
	

}
