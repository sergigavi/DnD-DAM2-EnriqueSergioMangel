<<<<<<< HEAD
package esm.dnd.DnDDAM2EnriqueSergioMangel.modelo;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.stereotype.Component;

import java.util.List;

import com.mongodb.lang.Nullable;

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

@Component
@Document
public class Equipamiento {

	@Id
	@NonNull
	@EqualsAndHashCode.Include
	private ObjectId idEquipo;
	
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
	private Integer precio;
	
	@Nullable
	private Float peso;
		
	@Nullable
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
=======
package esm.dnd.DnDDAM2EnriqueSergioMangel.modelo;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.stereotype.Component;

import java.util.List;

import com.mongodb.lang.Nullable;

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

@Component
@Document
public class Equipamiento {

	@Id
	@NonNull
	@EqualsAndHashCode.Include
	private ObjectId idEquipo;
	
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
	private Integer precio;
	
	@Nullable
	private Float peso;
		
	@Nullable
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
>>>>>>> main
