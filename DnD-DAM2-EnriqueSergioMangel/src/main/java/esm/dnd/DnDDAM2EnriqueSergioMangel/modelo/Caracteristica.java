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

public class Caracteristica {
		
	private String nombre;

	//campo autoevaluado
	private String nombreIniciales;

	private int valorTotal;

	//campo autoevaluado
	private int valorMod;


	//Para crear una caracteristica se deberia poder introducir solo el nombre (Fuerza, destreza...)
	//y su valor (1-20). Con esos datos deberia calcular el modificador y crear las iniciales
	// (Las iniciales es una idea que estaba considerando, puramente estetico)


	//El constructor por defecto cuando hacemos una característica (basicamente cuando creamos una ficha de personaje vacia)
	//Nos pone el valor total a 10 y tiene que recibir un nombre. Este nombre lo recibe de la ficha de personaje
	public Caracteristica(String nombre){
		this.valorTotal=10;
		this.valorMod=calcValorMod(this.valorTotal);
		this.nombre=nombre;
		this.nombreIniciales=setNombreIniciales(this.nombre);
	}

	public void setNombreInicialesPorNombre(String nombre){
		this.nombreIniciales=setNombreIniciales(nombre);
	}

	public void setValorModPorValorTotal(Integer valor){
		this.valorMod=calcValorMod(valor);
	}

	public Caracteristica(String nombre,Integer valorTotal){

		this.nombre=nombre;
		this.valorTotal=valorTotal;
		this.nombreIniciales=setNombreIniciales(nombre);
		this.valorMod=calcValorMod(valorTotal);

	}

	public static String setNombreIniciales(String nombre){

		switch (nombre) {
			case "Fuerza":
				return "Fue";
			case "Destreza":
				return "Des";
			case "Constitucion":
				return "Con";
			case "Inteligencia":
				return "Int";
			case "Sabiduria":
				return "Sab";
			case "Carisma":
				return "Car";
			default:
				return "???";
		}
	}

	public static Integer calcValorMod(Integer valor){
		
		if(valor<10){
			if(valor%2!=0){
				valor=valor-1;
			}
			switch (valor) {
				case 8:
					return -1;
				case 6:
					return -2;
				case 4:
					return -3;
				case 2:
					return -4;
				case 0:
					return -5;
				default:
					return 0;
			}
		}else if(valor>=10){
			if(valor%2!=0){
				valor=valor-1;
			}
			switch (valor) {
				case 10:
					return 0;
				case 12:
					return 1;
				case 14:
					return 2;
				case 16:
					return 3;
				case 18:
					return 4;
				case 20:
					return 5;
				default:
					return 0;
			}
		}else{
			return 0;
		}
	}
}

