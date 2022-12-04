package esm.dnd.DnDDAM2EnriqueSergioMangel.modelo;

import java.util.stream.Stream;

public enum CatEquipo {

	ARMADURA_INTERMEDIA{public String toString(){return "ARMADURA_INTERMEDIA";}},
	ARMADURA_LIGERA{public String toString(){return "ARMADURA_LIGERA";}}, 
	ARMADURA_PESADA{public String toString(){return "ARMADURA_PESADA";}},
	ARMA_MARCIAL{public String toString(){return "ARMA_MARCIAL";}}, 
	ARMA_SENCILLA{public String toString(){return "ARMA_SENCIALLA";}}, 
	ESCUDO{public String toString(){return "ESCUDO";}}, 
	HERRAMIENTA{public String toString(){return "HERRAMIENTA";}}, 
	INSTRUMENTO_MUSICAL{public String toString(){return "INSTRUMENTO_MUSICAL";}},
	JUEGO{public String toString(){return "JUEGO";}}, 
	KIT{public String toString(){return "KIT";}}, 
	MONTURA{public String toString(){return "MONTURA";}}, 
	MUNICION{public String toString(){return "MUNICION";}}, 
	OTROS{public String toString(){return "OTROS";}}, 
	PAQUETE_DE_EQUIPO{public String toString(){return "PAQUETE_DE_EQUIPO";}}, 
	VEHICULO{public String toString(){return "VEHICULO";}};

	public static CatEquipo getCatEquipo(String catEquipo){
		return Stream.of(CatEquipo.values())
			.filter(e->catEquipo.equals(e.toString()))
			.findFirst().orElse(CatEquipo.ESCUDO);
	}
}
