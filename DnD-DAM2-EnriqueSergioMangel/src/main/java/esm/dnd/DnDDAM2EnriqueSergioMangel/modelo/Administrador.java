package esm.dnd.DnDDAM2EnriqueSergioMangel.modelo;


import java.util.UUID;

import org.springframework.data.annotation.Id;

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
import lombok.NonNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Builder

public class Administrador {
	
	@NonNull
	@Id
	@EqualsAndHashCode.Include
	private UUID idAdmin;
	
	private String nombre;
	
	private String email;
	
	private String contrasenia;
	
	

}
