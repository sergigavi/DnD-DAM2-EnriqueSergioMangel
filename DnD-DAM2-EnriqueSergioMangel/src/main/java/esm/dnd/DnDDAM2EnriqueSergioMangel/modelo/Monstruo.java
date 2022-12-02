package esm.dnd.DnDDAM2EnriqueSergioMangel.modelo;

import java.util.ArrayList;
import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.stereotype.Component;

import com.mongodb.lang.NonNull;
import com.mongodb.lang.Nullable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@AllArgsConstructor
@Builder
@EqualsAndHashCode(onlyExplicitlyIncluded = true)

@Component
@Document
public class Monstruo {
    
    @Id
    @NonNull
    @EqualsAndHashCode.Include
    private ObjectId idMonstruo;

    @Nullable
    private String nombre;
    @Nullable
    private List<Caracteristica> caracteristicas;
    @Nullable
    private int ca;
    @Nullable
    private Clase clase;
    @Nullable
    private Raza raza;
    @Nullable
    private Alineamiento alineamiento;
    @Nullable
    private int nivel;
    @Nullable
    private int bonifCompetencia;
    @Nullable
    private int puntosVidaMax;
    @Nullable
    private int velocidad;
    @Nullable
    private String rasgos;
    @Nullable
    private List<Habilidad> habilidades;
    @Nullable
    private int desafio;

    public Monstruo(){
        this.idMonstruo=ObjectId.get();
        this.nombre="";
        this.nivel=1;
        this.bonifCompetencia=calcBonifCompetencia(this.nivel);
        this.caracteristicas=setCaracteristicasPorDefecto();
        this.habilidades=setHabilidadesPorDefecto(caracteristicas, this.bonifCompetencia);
        this.desafio=1;
        this.rasgos="";
        this.puntosVidaMax=0;
        this.velocidad=30;
        this.ca=10;
        this.clase=Clase.SELECCIONAR;
        this.raza=Raza.SELECCIONAR;
    }
    public Monstruo(Monstruo monstruo){
        this.idMonstruo=ObjectId.get();
        this.nivel=monstruo.nivel;
        this.nombre=monstruo.nombre;
        this.bonifCompetencia=calcBonifCompetencia(this.nivel);
        this.caracteristicas=monstruo.getCaracteristicas();
        this.habilidades=setHabilidades(caracteristicas, habilidades, this.bonifCompetencia);
        this.rasgos=monstruo.rasgos;
        this.alineamiento=monstruo.alineamiento;
        this.puntosVidaMax=monstruo.puntosVidaMax;
        this.ca=monstruo.ca;
        this.desafio=monstruo.desafio;
        this.velocidad=monstruo.velocidad;
        this.raza=monstruo.raza;
        this.clase=monstruo.clase;
    }

    public List<Habilidad> setHabilidades(List<Caracteristica> caracteristicas,List<Habilidad> habilidades,Integer bonif){

		int fueCaract = caracteristicas.stream().filter(car->car.getNombreIniciales().equals("Fue"))
			.mapToInt(car->car.getValorMod()).findFirst().orElse(0);

		int desCaract = caracteristicas.stream().filter(car->car.getNombreIniciales().equals("Des"))
			.mapToInt(car->car.getValorMod()).findFirst().orElse(0);
		
		int intCaract = caracteristicas.stream().filter(car->car.getNombreIniciales().equals("Int"))
			.mapToInt(car->car.getValorMod()).findFirst().orElse(0);

		int sabCaract = caracteristicas.stream().filter(car->car.getNombreIniciales().equals("Sab"))
			.mapToInt(car->car.getValorMod()).findFirst().orElse(0);

		int carCaract = caracteristicas.stream().filter(car->car.getNombreIniciales().equals("Car"))
			.mapToInt(car->car.getValorMod()).findFirst().orElse(0);

		List<Habilidad> hs=new ArrayList<>();

		habilidades.stream().forEach((hab)->{
			switch (hab.getNombre()) {
				case "Acrobacias":
					hab.setMod(hab.getCompetencia(),desCaract, bonif);
					hs.add(hab);
					break;
				case "C.Arcanos":
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
				case "Juego de manos":
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
				case "Perpicacia":
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
				case "Trato con animales":
					hab.setMod(hab.getCompetencia(), sabCaract, bonif);
					hs.add(hab);
					break;
				default:
					break;
			}
		});
		return hs;
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
}
