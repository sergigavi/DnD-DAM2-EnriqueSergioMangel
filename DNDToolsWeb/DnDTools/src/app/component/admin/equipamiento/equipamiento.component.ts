import { ComponentPortal } from '@angular/cdk/portal';
import { AfterViewInit, Component, Inject, OnInit, ViewChild} from '@angular/core';
import { NgForm } from '@angular/forms';
import { MatDialog, MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { MatPaginator } from '@angular/material/paginator';
import { MatSort } from '@angular/material/sort';
import { MatTableDataSource } from '@angular/material/table';
import { Router } from '@angular/router';
import { EquipamientoAdminService } from 'src/app/services/equipamientoAdmin-service/equipamiento-admin.service';
import { DialogCrearEquipo } from './DialogCrearEquipo';

export interface EquipamientoAdmin{
  idEquipo:any
  idString:String;
  nombre:String;
  tipo:Tipo;
  categoria:CatEquipo;
  propiedad:PropiedadEquipo[];
  modificador:String;
  danio:String;
  alcance:number;
  precio:String;
  peso:String;
  descripcion:String;
}

export enum Tipo{
  ARMADURA="ARMADURA",
  ARMA="ARMA",
  EQUIPO_DE_AVENTURAS="EQUIPO_DE_AVENTURAS"
}
export enum CatEquipo{
  ARMADURA_INTERMEDIA="ARMADURA_INTERMEDIA",
  ARMADURA_LIGERA="ARMADURA_LIGERA",
  ARMADURA_PESADA="ARMADURA_PESADA",
	ARMA_MARCIAL="ARMA_MARCIAL",
  ARMA_SENCILLA="ARMA_SENCILLA",
  ESCUDO="ESCUDO",
  HERRAMIENTA="HERRAMIENTA",
  INSTRUMENTO_MUSICAL="INSTRUMENTO_MUSICAL",
	JUEGO="JUEGO",
  KIT="KIT",
  MONTURA="MONTURA",
  MUNICION="MUNICION",
  OTROS="OTROS",
  PAQUETE_DE_EQUIPO="PAQUETE_DE_EQUIPO",
  VEHICULO="VEHICULO"
}

export enum PropiedadEquipo{
  ALCANCE="ALCANCE",
  ARROJADIZO="ARROJADIZO",
  CARGADOR="CARGADOR",
  DE_CARGA="DE_CARGA",
  DESVENTAJA="DESVENTAJA",
  DISTANCIA="DISTANCIA",
  DOS_MANOS="DOS_MANOS",
  ESPECIAL="ESPECIAL",
  FOCO_ARCANO="FOCO_ARCANO",
  FOCO_DUIDRICO="FOCO_DUIDRICO",
	FUERZA="FUERZA",
  LIGERO="LIGERO",
  MUNICION="MUNICION",
  MUNICION_ESPECIAL="MUNICION_ESPECIAL",
  PESADO="PESADO",
  RAFAGA="RAFAGA",
  SIMBOLO_SAGRADO="SIMBOLO_SAGRADO",
  SINTONIZADO="SINTONIZADO",
  SUTIL="SUTIL",
  VERSATIL="VERSATIL"
}

export enum Modificador{
  FUE="FUE",
  DES="DES",
  INT="INT",
  SAB="SAB",
  CAR="CAR"
}


@Component({
  selector: 'app-equipamiento',
  templateUrl: './equipamiento.component.html',
  styleUrls: ['./equipamiento.component.css']
})


export class EquipamientoComponent implements OnInit,AfterViewInit   {
  title = 'Equipamiento';
  opened = false;
  equipo!:EquipamientoAdmin

  //data nombrado aqui
  data:EquipamientoAdmin[]=[];
  columnas:String[] = ["nombre","tipo","categoria","acceder","editar"]


  @ViewChild(MatPaginator,{static:true}) paginator! :MatPaginator
  @ViewChild(MatSort,{static:true}) sort!:MatSort

  dataSource = new MatTableDataSource<EquipamientoAdmin>([]);

  constructor(private dialog : MatDialog,private equipamientoService: EquipamientoAdminService,private router:Router){
  }

  ngOnInit(): void {
    this.dataSource.paginator=this.paginator
    this.dataSource.sort=this.sort
    this.showEquipo()
  }

  ngAfterViewInit(){
    this.dataSource.paginator=this.paginator
    this.dataSource.sort=this.sort
  }

  toggleSidebar() {
    this.opened = !this.opened;
  }

  applyFilter(event:Event){
    const filterValue = (event.target as HTMLInputElement).value
    this.dataSource.filter = filterValue.trim().toLowerCase()

    if(this.dataSource.paginator){
      this.dataSource.paginator.firstPage()
    }
  }

  onDeleteAll(){
    if(confirm("¿Borrar todos los elementos?")){
      this.equipamientoService.deleteAllEquipo().subscribe((data:any)=>{
        if(data==true){
          alert("Datos borrados")
          this.showEquipo()
        }
      })
    }
  }

  public showEquipo(){
    this.equipamientoService.getAll().subscribe((data:any)=>{
      this.dataSource.data=data
      console.log(data)
    })
  }

  public navegar(ruta:String){
    this.router.navigate([`${ruta}`])
  }

  public crearEquipo(){

    const dialogRef=this.dialog.open(DialogCrearEquipo,{
      width: '100%',
    });

    dialogRef.afterClosed().subscribe((data:any)=>{
      console.log("cerrado")
      this.showEquipo();
    });
  }

  public verEquipo(equipo:EquipamientoAdmin){
    this.equipo=equipo
    const dialogRef=this.dialog.open(DialogVerEquipo,{
      data:{equipo:this.equipo},
      width:'100%'
    });

    dialogRef.afterClosed().subscribe((data:any)=>{
      this.showEquipo()
    });
  }

  public editarEquipo(equipo:EquipamientoAdmin){
    this.equipo=equipo;
    const dialogRef=this.dialog.open(DialogEditarEquipo,{
      data:{equipo:this.equipo},
      width:'100%'
    })

    dialogRef.afterClosed().subscribe((data:any)=>{
      this.showEquipo();
    })
  }
}

@Component({
  selector: 'dialogVerEquipo',
  templateUrl: './dialogVerEquipo.html'
})
export class DialogVerEquipo{
  equipo:any;
  constructor(public dialogRef:MatDialogRef<EquipamientoComponent>,private equipamientoServicio:EquipamientoAdminService,
    @Inject(MAT_DIALOG_DATA) public data: EquipamientoComponent){}

    ngOnInit(){
      this.equipo=this.data['equipo']
    }
    onNoClick(){
      this.dialogRef.close()
    }
}

@Component({
  selector: 'dialogEditarEquipo',
  templateUrl: 'dialogEditarEquipo.html'
})
export class DialogEditarEquipo{
  equipo:any;
  constructor(public dialogRef:MatDialogRef<EquipamientoComponent>,private equipamientoServicio:EquipamientoAdminService,
    @Inject(MAT_DIALOG_DATA) public data:EquipamientoComponent){}

    tipos=Object.values(Tipo)
    categorias=Object.values(CatEquipo)
    propiedades=Object.values(PropiedadEquipo)
    modificadores=Object.values(Modificador)

    tipoElegido:string="";
    categoriaElegida:string="";
    propiedadElegida:string[]=[]
    modificadorElegido:string="";
    equipamiento!:EquipamientoAdmin
    modificador:string=""


    onSubmit(equipoForm:NgForm){
      this.equipamiento=equipoForm.value
      this.equipamiento.idEquipo=this.equipo.idEquipo
      this.equipamiento.idString=this.equipo.idString
      console.log(this.equipamiento)
      if(this.equipamiento.propiedad.length<1){
        this.equipamiento.propiedad=[]
      }
      this.equipamientoServicio.updateEquipo(this.equipamiento).subscribe((data:any)=>{
        this.dialogRef.close()
      })
    }

    ngOnInit(){
      this.equipo=this.data['equipo']
      this.equipamiento=this.equipo
      this.modificador=this.equipamiento.modificador.toString()
      console.log(this.equipo)
    }
    onNoClick(){
      this.dialogRef.close()
    }

    onDelete(){
      if(confirm("¿Borrar este equipo?")){
        this.equipamientoServicio.deleteEquipoById(this.equipamiento.idString).subscribe((data:any)=>{
          this.dialogRef.close()
        })
      }
    }
}
