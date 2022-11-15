package esm.dnd.DnDDAM2EnriqueSergioMangel.modelo;

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

public class Habilidad {
		
	private String nombre;
	
	private Boolean competencia;

	//campo autoevaluado
	private int mod;

	//Las habilidades dependen de la caracteristica con la que escalen (Fue,Des...) 

	//Usamos la funcion calcMod pasandole el bonifCompetencia del personaje, el mod de la habilidad y si es competente o no
	public Habilidad(String nombre,Boolean competencia,Integer modCaracteristica,Integer bonif){
		this.nombre=nombre;
		this.competencia=competencia;
		this.mod=calcMod(competencia,modCaracteristica,bonif);
	}

	public static Integer calcMod(Boolean competencia,Integer modCaracteristica,Integer bonif){
		
		if(competencia){
			return modCaracteristica+bonif;
		}else{
			return modCaracteristica;
		}
	}

	public void setMod(Boolean competencia,Integer modCaracteristica,Integer bonif){
		this.mod=calcMod(competencia, modCaracteristica, bonif);
	}
}
