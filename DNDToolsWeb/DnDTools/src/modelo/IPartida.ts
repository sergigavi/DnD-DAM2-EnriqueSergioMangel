import { IEquipo } from "./IEquipo";
import { IFichaPersonaje } from "./IFichaPersonaje";
import { IUsuario } from "./IUsuario";

export interface IPartida{
    idPartida:any;
    idStringPartida:String;
    codigoPartida:String;
    nombre:String;
    creador:IUsuario;
    usuariosPartida:IUsuario[];
    equipoPartida:IEquipo[];
    fichasPartida:IFichaPersonaje[];
}
