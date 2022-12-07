package esm.dnd.DnDDAM2EnriqueSergioMangel.modelo;

import java.util.stream.Stream;


public enum Alineamiento {

	LEGAL_BUENO{public String toString(){return "LEGAL_BUENO";}}, 
	NEUTRAL_BUENO{public String toString(){return "NEUTRAL_BUENO";}}, 
	CAOTICO_BUENO{public String toString(){return "CAOTICO_BUENO";}}, 
	LEGAL_NEUTRAL{public String toString(){return "LEGAL_NEUTRAL";}}, 
	NEUTRAL{public String toString(){return "NEUTRAL";}}, 
	CAOTICO_NEUTRAL{public String toString(){return "CAOTICO_NEUTRAL";}}, 
	LEGAL_MALO{public String toString(){return "LEGAL_MALO";}}, 
	NEUTRAL_MALO{public String toString(){return "NEUTRAL_MALO";}}, 
	CAOTICO_MALO{public String toString(){return "CAOTICO_MALO";}}, 
	SELECCIONAR{public String toString(){return "SELECCIONAR";}};

	public static Alineamiento getAlineamiento(String alineamiento){
		return Stream.of(Alineamiento.values())
			.filter(a->alineamiento.equals(a.toString()))
			.findFirst().orElse(Alineamiento.SELECCIONAR);
	}
}
