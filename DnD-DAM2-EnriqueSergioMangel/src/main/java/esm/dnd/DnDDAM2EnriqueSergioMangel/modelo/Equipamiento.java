package esm.dnd.DnDDAM2EnriqueSergioMangel.modelo;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.MongoId;

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

	@MongoId
	@NonNull
	@EqualsAndHashCode.Include
	private ObjectId idEquipo;
	
	private String nombre;
	
	private TipoEquipo tipo;
	
	private CatEquipo categoria;
	
	private PropiedadEquipo propiedad;
	
	private String modificador;
	
	private String danio;
	
	private Integer alcance;
	
	private Integer precio;
	
	private Float peso;
		
	private String descripcion;
	
	public void deleteById(ObjectId id){

		if(id==this.idEquipo){
			this.idEquipo=null;
			this.nombre=null;
			this.tipo=null;
			this.categoria=null;
			this.propiedad=null;
			this.modificador=null;
			this.danio=null;
			this.alcance=null;
			this.precio=null;
			this.peso=null;
			this.descripcion=null;
		}
	}
}
