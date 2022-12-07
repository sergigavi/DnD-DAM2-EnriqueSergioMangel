package esm.dnd.DnDDAM2EnriqueSergioMangel.modelo;

import java.util.stream.Stream;

public enum Clase {

	BARBARO{public String toString(){return "BARBARO";}}, 
	BARDO{public String toString(){return "BARDO";}}, 
	BRUJO{public String toString(){return "BRUJO";}}, 
	CLERIGO{public String toString(){return "CLERIGO";}}, 
	DRUIDA{public String toString(){return "DRUIDA";}}, 
	EXPLORADOR{public String toString(){return "EXPLORADOR";}}, 
	GUERRERO{public String toString(){return "GUERRERO";}}, 
	HECHICERO{public String toString(){return "HECHICERO";}}, 
	MAGO{public String toString(){return "MAGO";}}, 
	MONJE{public String toString(){return "MONJE";}}, 
	PALADIN{public String toString(){return "PALADIN";}}, 
	PICARO{public String toString(){return "PICARO";}}, 
	SELECCIONAR{public String toString(){return "SELECCIONAR";}};

	public static Clase getClase(String clase){
		return Stream.of(Clase.values())
			.filter(c->clase.equals(c.toString()))
			.findFirst()
			.orElse(Clase.SELECCIONAR);
	}
}
