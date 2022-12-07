package esm.dnd.DnDDAM2EnriqueSergioMangel.modelo;

import java.util.stream.Stream;

public enum Raza {

	DRACONIDO{public String toString(){return "DRACONIDO";}}, 
	ELFO{public String toString(){return "ELFO";}}, 
	ENANO{public String toString(){return "ENANO";}}, 
	GNOMO{public String toString(){return "GNOMO";}}, 
	HUMANO{public String toString(){return "HUMANO";}}, 
	MEDIANO{public String toString(){return "MEDIANO";}}, 
	SEMIELFO{public String toString(){return "SEMIELFO";}}, 
	SEMIORCO{public String toString(){return "SEMIORCO";}}, 
	TIFLIN{public String toString(){return "TIFLIN";}}, 
	SELECCIONAR{public String toString(){return "SELECCIONAR";}};

	public static Raza getCRaza(String raza){
		return Stream.of(Raza.values())
			.filter(r->raza.equals(r.toString()))
			.findFirst().orElse(Raza.SELECCIONAR);
	}
}
