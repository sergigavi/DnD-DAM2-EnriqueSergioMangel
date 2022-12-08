package esm.dnd.DnDDAM2EnriqueSergioMangel.modelo;


import java.util.ArrayList;
import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;
import org.springframework.stereotype.Component;

import com.mongodb.lang.Nullable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@AllArgsConstructor
@Builder
@Component
@Document
public class FichaPersonaje {
	
	@MongoId
	private ObjectId idFichaPersonaje;
	
	@EqualsAndHashCode.Include
	@Nullable
	private String idFichaPersonajeString;

	@Nullable
	private Usuario usuario;
	
	private String nombre;

	//	Estos 3 se guardan embebidos ya que mongo lo permite
	@Nullable
	private List<Equipamiento> inventario;
	@Nullable
	private List<Caracteristica> caracteristicas;
	@Nullable
	private List<Habilidad> habilidades;
	@Nullable
	private Clase clase;
	@Nullable
	private Raza raza;
	@Nullable
	private Alineamiento alineamiento;
	@Nullable
	private int nivel;
	@Nullable
	//campo autoevaluado
	private int bonifCompetencia;
	
	@Nullable
	private String transfondo;
	@Nullable
	private int ca;
	@Nullable
	private int velocidad;
	@Nullable
	private int puntosVidaMax;
	
	//posible para quitar
	@Nullable
	private int puntosVidaAct;

	@Nullable
	private String rasgosPersonalidad;
	
	@Nullable
	private String ideales;
	
	@Nullable
	private String vinculos;
	
	@Nullable
	private String defectos;
	
	@Nullable
	private String rasgosAtt;
	
	@Nullable
	private String otrasComp;
	
	@Nullable
	private String apariencia;
	
	@Nullable
	private String historiaPersonal;
	
	@Nullable
	private String rasgos;
	
	@Nullable
	private String notasAdd;

	public FichaPersonaje(Integer nivel){
		this.bonifCompetencia=calcBonifCompetencia(nivel);
	}
	
	public void setBonifCompetenciaPorNivel(Integer nivel){

		this.bonifCompetencia=calcBonifCompetencia(nivel);
	}

	/*
	public void deleteEquipamiento(Equipamiento equipamiento){
		this.inventario.stream()
			.filter(i->i.getIdEquipo().equals(equipamiento.getIdEquipo()))
			.forEach(e->e.);
	}
	 */
	public FichaPersonaje(){

		this.idFichaPersonaje=ObjectId.get();
		this.idFichaPersonajeString=this.idFichaPersonaje.toString();
		this.nombre="FICHA_VACIA";
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
		this.inventario= new ArrayList<Equipamiento>();
	}
	
	public FichaPersonaje(FichaPersonaje ficha){

		System.out.println(ficha.getNivel());
		this.idFichaPersonaje=ficha.getIdFichaPersonaje();
		this.idFichaPersonajeString=ficha.getIdFichaPersonajeString();
		this.usuario = ficha.getUsuario();
		this.inventario=ficha.getInventario();
		this.caracteristicas=ficha.getCaracteristicas();
		if(ficha.getNivel()>20){
			ficha.setNivel(20);
		}
		if(ficha.getNivel()<1){
			ficha.setNivel(1);
		}
		this.nivel=ficha.getNivel();
		this.nombre=ficha.getNombre();
		this.bonifCompetencia=calcBonifCompetencia(this.nivel);
		this.habilidades=setHabilidadesNuevas(this.caracteristicas, ficha.getHabilidades(), null);
		this.clase=ficha.getClase();
		this.raza=ficha.getRaza();
		this.alineamiento=getAlineamiento();
		this.transfondo=ficha.getTransfondo();
		this.ca=ficha.getCa();
		this.velocidad=ficha.getVelocidad();
		this.puntosVidaMax=ficha.getPuntosVidaMax();
		this.puntosVidaAct=ficha.getPuntosVidaAct();
		this.vinculos=ficha.getVinculos();
		this.rasgos=ficha.getRasgos();
		this.rasgosAtt=ficha.getRasgosAtt();
		this.apariencia=ficha.getApariencia();
		this.ideales=ficha.getIdeales();
		this.otrasComp=ficha.getOtrasComp();
		this.historiaPersonal=ficha.getHistoriaPersonal();
		this.notasAdd=ficha.getNotasAdd();
		this.defectos=ficha.getDefectos();
		this.rasgosPersonalidad=ficha.getRasgosPersonalidad();
	}
	

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

	public List<Habilidad> setHabilidadesNuevas(List<Caracteristica> caracteristicas,List<Habilidad> habilidades,Integer bonif){


		int fueCaract = caracteristicas.stream().filter(car->car.getNombreIniciales().equals("Fue"))
			.map(car->car.getValorMod()).findFirst().orElse(0);

		int desCaract = caracteristicas.stream().filter(car->car.getNombreIniciales().equals("Des"))
			.map(car->car.getValorMod()).findFirst().orElse(0);
		
		int intCaract = caracteristicas.stream().filter(car->car.getNombreIniciales().equals("Int"))
			.map(car->car.getValorMod()).findFirst().orElse(0);

		int sabCaract = caracteristicas.stream().filter(car->car.getNombreIniciales().equals("Sab"))
			.map(car->car.getValorMod()).findFirst().orElse(0);

		int carCaract = caracteristicas.stream().filter(car->car.getNombreIniciales().equals("Car"))
			.map(car->car.getValorMod()).findFirst().orElse(0);


		List<Habilidad> hs=new ArrayList<>();

		habilidades.stream().forEach((hab)->{
			switch (hab.getNombre()) {
				case "Acrobacias":
					hab.setMod(hab.getCompetencia(),desCaract, bonif);
					hs.add(hab);
					break;
				case "C_Arcanos":
					hab.setMod(hab.getCompetencia(), intCaract, bonif);
					hs.add(hab);
					break;
				case "Enganiar":
					hab.setMod(hab.getCompetencia(), carCaract, bonif);
					hs.add(hab);
					break;
				case "Historia":
					hab.setMod(hab.getCompetencia(), intCaract, bonif);
					hs.add(hab);
					break;
				case "Interpretacion":
					hab.setMod(hab.getCompetencia(), carCaract, bonif);
					hs.add(hab);
					break;
				case "Intimidar":
					hab.setMod(hab.getCompetencia(), carCaract, bonif);
					hs.add(hab);
					break;
				case "Investigar":
					hab.setMod(hab.getCompetencia(), intCaract, bonif);
					hs.add(hab);
					break;
				case "Juego_de_manos":
					hab.setMod(hab.getCompetencia(), desCaract, bonif);
					hs.add(hab);
					break;
				case "Atletismo":
					hab.setMod(hab.getCompetencia(), fueCaract, bonif);
					hs.add(hab);
					break;
				case "Medicina":
					hab.setMod(hab.getCompetencia(), sabCaract, bonif);
					hs.add(hab);
					break;
				case "Naturaleza":
					hab.setMod(hab.getCompetencia(), sabCaract, bonif);
					hs.add(hab);
					break;
				case "Percepcion":
					hab.setMod(hab.getCompetencia(), sabCaract, bonif);
					hs.add(hab);
					break;
				case "Perspicacia":
					hab.setMod(hab.getCompetencia(), sabCaract, bonif);
					hs.add(hab);
					break;
				case "Persuasion":
					hab.setMod(hab.getCompetencia(), carCaract, bonif);
					hs.add(hab);
					break;
				case "Religion":
					hab.setMod(hab.getCompetencia(), intCaract, bonif);
					hs.add(hab);
					break;
				case "Sigilo":
					hab.setMod(hab.getCompetencia(), desCaract, bonif);
					hs.add(hab);
					break;
				case "Supervivencia":
					hab.setMod(hab.getCompetencia(), sabCaract, bonif);
					hs.add(hab);
					break;
				case "Trato_con_animales":
					hab.setMod(hab.getCompetencia(), sabCaract, bonif);
					hs.add(hab);
					break;
			}
		});
		return hs;
	}

	public static List<Habilidad> setHabilidadesPorDefecto(List<Caracteristica> car,Integer bonif){
		final int _numHabilidades = 18;
		List<Habilidad> hab=new ArrayList<Habilidad>();
		
		for(int i=0;i<_numHabilidades;i++){
			Habilidad h=new Habilidad();
			switch (i) {
				case 0:
					h=new Habilidad("Acrobacias",false,0,bonif);
					hab.add(h);
					break;
				case 1:
					h=new Habilidad("C_Arcanos",false,0,bonif);
					hab.add(h);
					break;
				case 2:
					h=new Habilidad("Enganiar",false,0,bonif);
					hab.add(h);
					break;
				case 3:
					h=new Habilidad("Historia",false,0,bonif);
					hab.add(h);
					break;
				case 4:
					h=new Habilidad("Interpretacion",false,0,bonif);
					hab.add(h);
					break;
				case 5:
					h=new Habilidad("Intimidar",false,0,bonif);
					hab.add(h);
					break;
				case 6:
				h=new Habilidad("Investigacion",false,0,bonif);
					hab.add(h);
					break;
				case 7:
					h=new Habilidad("Juego_de_manos",false,0,bonif);
					hab.add(h);
					break;
				case 8:
					h=new Habilidad("Atletismo",false,0,bonif);
					hab.add(h);
					break;
				case 9:
					h=new Habilidad("Medicina",false,0,bonif);
					hab.add(h);
					break;
				case 10:
					h=new Habilidad("Naturaleza",false,0,bonif);
					hab.add(h);
					break;
				case 11:
					h=new Habilidad("Percepcion",false,0,bonif);
					hab.add(h);
					break;
				case 12:
					h=new Habilidad("Perpicacia",false,0,bonif);
					hab.add(h);
					break;
				case 13:
					h=new Habilidad("Persuasion",false,0,bonif);
					hab.add(h);
					break;
				case 14:
					h=new Habilidad("Religion",false,0,bonif);
					hab.add(h);
					break;
				case 15:
					h=new Habilidad("Sigilo",false,0,bonif);
					hab.add(h);
					break;
				case 16:
					h=new Habilidad("Supervivencia",false,0,bonif);
					hab.add(h);
					break;
				case 17:
					h=new Habilidad("Trato_con_animales",false,0,bonif);
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
	



