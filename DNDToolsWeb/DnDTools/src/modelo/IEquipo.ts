import { EnumCatEquipo } from "./EnumCatEquipo";
import { EnumPropiedadEquipo } from "./EnumPropiedadEquipo";
import { EnumTipo } from "./EnumTipo";

export interface IEquipo{
  idEquipo:any
  idString:String;
  nombre:String;
  tipo:EnumTipo;
  categoria:EnumCatEquipo;
  propiedad:EnumPropiedadEquipo[];
  modificador:String;
  danio:String;
  alcance:number;
  precio:String;
  peso:String;
  descripcion:String;
}
