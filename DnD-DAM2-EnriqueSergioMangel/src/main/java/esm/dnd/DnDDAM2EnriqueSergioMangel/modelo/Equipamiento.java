package esm.dnd.DnDDAM2EnriqueSergioMangel.modelo;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;
import org.springframework.stereotype.Component;

import java.util.List;

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

@Component
@Document
public class Equipamiento {

	@MongoId
	private ObjectId idEquipo;

	@EqualsAndHashCode.Include
	@Nullable
	private String idString;
	
	@Nullable
	private String nombre;
	
	@Nullable
	private TipoEquipo tipo;
	
	@Nullable
	private CatEquipo categoria;
	
	@Nullable
	private List<PropiedadEquipo> propiedad;
	
	@Nullable
	private String modificador;
	
	@Nullable
	private String danio;
	
	@Nullable
	private Integer alcance;
	
	@Nullable
	private String precio;
	
	@Nullable
	private Float peso;
		
	@Nullable
	private String descripcion;
	
	public void deleteById(ObjectId id){

		if(id==this.idEquipo){
			this.idEquipo=null;
			this.idString=null;
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
