package esm.dnd.DnDDAM2EnriqueSergioMangel.modelo;

import java.util.stream.Stream;

public enum PropiedadEquipo {

	ALCANCE{public String toString(){return "ALCANCE";}}, 
	ARROJADIZO{public String toString(){return "ARROJADIZO";}}, 
	CARGADOR{public String toString(){return "CARGADOR";}}, 
	DE_CARGA{public String toString(){return "DE_CARGA";}}, 
	DESVENTAJA{public String toString(){return "DESVENTAJA";}}, 
	DISTANCIA{public String toString(){return "DISTANCIA";}}, 
	DOS_MANOS{public String toString(){return "DOS_MANOS";}}, 
	ESPECIAL{public String toString(){return "ESPECIAL";}}, 
	FOCO_ARCANO{public String toString(){return "FOCO_ARCANO";}}, 
	FOCO_DUIDRICO{public String toString(){return "FOCO_DUIDRICO";}}, 
	FUERZA{public String toString(){return "FUERZA";}}, 
	LIGERO{public String toString(){return "LIGERO";}}, 
	MUNICION{public String toString(){return "MUNICION";}}, 
	MUNICION_ESPECIAL{public String toString(){return "MUNICION_ESPECIAL";}}, 
	PESADO{public String toString(){return "PESADO";}}, 
	RAFAGA{public String toString(){return "RAFAGA";}}, 
	SIMBOLO_SAGRADO{public String toString(){return "SIMBOLO_SAGRADO";}}, 
	SINTONIZADO{public String toString(){return "SINTONIZADO";}}, 
	SUTIL{public String toString(){return "SUTIL";}}, 
	VERSATIL{public String toString(){return "VERSATIL";}};

	public static PropiedadEquipo getPropiedadEquipo(String propiedad){

		return Stream.of(PropiedadEquipo.values())
			.filter(p->propiedad.equals(p.toString()))
			.findFirst()
			.orElse(ALCANCE);
	}
}
