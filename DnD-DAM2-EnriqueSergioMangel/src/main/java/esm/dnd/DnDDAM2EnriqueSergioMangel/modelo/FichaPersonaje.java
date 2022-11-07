package esm.dnd.DnDDAM2EnriqueSergioMangel.modelo;


import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NonNull;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@AllArgsConstructor
@Builder
@Document
public class FichaPersonaje {
	
	@Id
	@NonNull
	@EqualsAndHashCode.Include
	private UUID idFichaPersonaje;
	
	private Usuario usuario;
	
	private String nombre;

	//	Estos 3 se guardan embebidos ya que mongo lo permite
	private List<Equipamiento> inventario;

	private List<Caracteristica> caracteristicas;
	
	private List<Habilidad> habilidades;

	private Clase clase;
	
	private Raza raza;
	
	private Alineamiento alineamiento;
	
	private int nivel;
	
	//campo autoevaluado
	private int bonifCompetencia;
	
	private String transfondo;
		
	private int ca;
	
	private int velocidad;
	
	private int puntosVidaMax;
	
	//posible para quitar
	private int puntosVidaAct;

	private String rasgosPersonalidad;
	
	private String ideales;
	
	private String vinculos;
	
	private String defectos;
	
	private String rasgosAtt;
	
	private String otrasComp;
	
	private String apariencia;
	
	private String historiaPersonal;
	
	private String rasgos;
	
	private String notasAdd;

	public FichaPersonaje(Integer nivel){
		this.bonifCompetencia=calcBonifCompetencia(nivel);
	}
	
	public void setBonifCompetenciaPorNivel(Integer nivel){

		this.bonifCompetencia=calcBonifCompetencia(nivel);
	}

	public FichaPersonaje(){

		this.idFichaPersonaje=UUID.randomUUID();
		this.nombre="";
		this.nivel=1;
		this.bonifCompetencia=calcBonifCompetencia(this.nivel);
		this.usuario=null;
		this.caracteristicas=setCaracteristicasPorDefecto();
		this.habilidades=setHabilidadesPorDefecto(this.caracteristicas,this.bonifCompetencia);
		this.clase=Clase.SELECCIONAR;
		this.raza=Raza.SELECCIONAR;
		this.alineamiento=Alineamiento.SELECCIONAR;
		this.transfondo="";
		this.ca=10;
		this.velocidad=30;
		this.puntosVidaMax=0;
		this.puntosVidaAct=0;
		this.vinculos="";
		this.rasgos="";
		this.rasgosAtt="";
		this.apariencia="";
		this.ideales="";
		this.otrasComp="";
		this.historiaPersonal="";
		this.notasAdd="";
		this.defectos="";
		this.rasgosPersonalidad="";
	}

	//estaba probando cosas con el constructor AllArgs
	/* 
	public FichaPersonaje(UUID id,Usuario usuario,String nombre,List<Equipamiento> inventario,
								List<Caracteristica> caracteristicas,
								List<Habilidad> habilidades,
								Clase clase,Raza raza,Alineamiento alineamiento,
								Integer nivel,Integer bonifCompetencia,String transfondo,Integer ca,Integer velocidad,
								Integer puntosVidaMax,Integer puntosVidaAct,String vinculos,
								String rasgos,String rasgosAtt,String apariencia,
								String ideales,String otrasComp,String historiaPersonal,String notasAdd,String defectos,
								String rasgosPersonalidad){

		this.idFichaPersonaje=id;
		this.usuario = usuario;
		this.inventario=inventario;
		this.caracteristicas=caracteristicas;
		

	}
	*/

	public static Integer calcBonifCompetencia(Integer nivel){

		if(nivel>=17){
			return 6;
		}else if(nivel>=13){
			return 5;
		}else if(nivel>=9){
			return 4;
		}else if(nivel>=5){
			return 3;
		}else {
			return 2;
		}
	}

	public static void setHabilidades(String nombre,Boolean integer,Integer modCar,Integer bonif){

		
	}

	public static List<Habilidad> setHabilidadesPorDefecto(List<Caracteristica> car,Integer bonif){
		final int _numHabilidades = 18;
		List<Habilidad> hab=new ArrayList<Habilidad>();
		
		for(int i=0;i<_numHabilidades;i++){
			Habilidad h=new Habilidad();
			switch (i) {
				case 1:
					h=new Habilidad("Acrobacias",false,0,bonif);
					hab.add(h);
					break;
				case 2:
					h=new Habilidad("C.Arcanos",false,0,bonif);
					hab.add(h);
					break;
				case 3:
					h=new Habilidad("Enganiar",false,0,bonif);
					hab.add(h);
					break;
				case 4:
					h=new Habilidad("Historia",false,0,bonif);
					hab.add(h);
					break;
				case 5:
					h=new Habilidad("Interpretacion",false,0,bonif);
					hab.add(h);
					break;
				case 6:
					h=new Habilidad("Intimidar",false,0,bonif);
					hab.add(h);
					break;
				case 7:
				h=new Habilidad("Investigacion",false,0,bonif);
					hab.add(h);
					break;
				case 8:
					h=new Habilidad("Juego de manos",false,0,bonif);
					hab.add(h);
					break;
				case 9:
					h=new Habilidad("Atletismo",false,0,bonif);
					hab.add(h);
					break;
				case 10:
					h=new Habilidad("Medicina",false,0,bonif);
					hab.add(h);
					break;
				case 11:
					h=new Habilidad("Naturaleza",false,0,bonif);
					hab.add(h);
					break;
				case 12:
					h=new Habilidad("Percepcion",false,0,bonif);
					hab.add(h);
					break;
				case 13:
					h=new Habilidad("Perpicacia",false,0,bonif);
					hab.add(h);
					break;
				case 14:
					h=new Habilidad("Persuasion",false,0,bonif);
					hab.add(h);
					break;
				case 15:
					h=new Habilidad("Religion",false,0,bonif);
					hab.add(h);
					break;
				case 16:
					h=new Habilidad("Sigilo",false,0,bonif);
					hab.add(h);
					break;
				case 17:
					h=new Habilidad("Supervivencia",false,0,bonif);
					hab.add(h);
					break;
				case 18:
					h=new Habilidad("Trato con animales",false,0,bonif);
					hab.add(h);
					break;
				default:
					break;
			}
		}
		return hab;
	}

	

	public static List<Caracteristica> setCaracteristicasPorDefecto(){
		final int _numCaracteristicas = 6;
		List<Caracteristica> car=new ArrayList<Caracteristica>();
		
		for(int i=0;i<_numCaracteristicas;i++){
			Caracteristica c=new Caracteristica();
			switch (i) {
				case 0:
					c=new Caracteristica("Fuerza");
					car.add(c);
					break;
				case 1:
					c=new Caracteristica("Destreza");
					car.add(c);
					break;
				case 2:
					c=new Caracteristica("Constitucion");
					car.add(c);
					break;
				case 3:
					c=new Caracteristica("Inteligencia");
					car.add(c);
					break;
				case 4:
					c=new Caracteristica("Sabiduria");
					car.add(c);
					break;
				case 5:
					c=new Caracteristica("Carisma");
					car.add(c);
					break;
				default:
					c=new Caracteristica("???");
					car.add(c);
					break;
			}
		}
		return car;
	}
}
	



