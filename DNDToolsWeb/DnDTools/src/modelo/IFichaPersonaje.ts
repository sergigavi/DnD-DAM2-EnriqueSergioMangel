import { EnumAlineamiento } from "./EnumAlineamiento";
import { EnumClase } from "./EnumClase";
import { EnumRaza } from "./EnumRaza";
import { ICaracteristica } from "./ICaracteristica";
import { IEquipo } from "./IEquipo";
import { IHabilidad } from "./IHabilidad";
import { IUsuario } from "./IUsuario";

export interface IFichaPersonaje{
  idFichaPersonaje:any;
  idFichaPersonajeString:String;
  usuario:IUsuario;
  nombre:String;
  inventario:IEquipo[];
  caracteristicas:ICaracteristica[];
  habilidades:IHabilidad[];
  clase:EnumClase;
  raza:EnumRaza;
  alineamiento:EnumAlineamiento;
  nivel:number;
  bonifCompetencia:number;
  transfondo:String;
  ca:number;
  velocidad:number;
  puntosVidaMax:number;
  puntosVidaAct:number;
  rasgosPersonalidad:String;
  ideales:String;
  vinculos:String;
  defectos:String;
  rasgosAtt:String;
  otrasComp:String;
  apariencia:String;
  historiaPersonal:String;
  rasgos:String;
  notasAdd:String;
}
