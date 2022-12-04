package esm.dnd.DnDDAM2EnriqueSergioMangel.modelo;

import java.util.stream.Stream;

public enum TipoEquipo {

		ARMADURA{public String toString() {return "ARMADURA";}}  ,
		ARMA{public String toString() {return "ARMA";}}  ,
		EQUIPO_DE_AVENTURAS{public String toString(){return "EQUIPO_DE_AVENTURAS";}};


		public static TipoEquipo getTipo(String tipo){

			return Stream.of(TipoEquipo.values())
				.filter(t->tipo.equals(t.toString()))
				.findFirst()
				.orElse(TipoEquipo.ARMA);
		}
}
