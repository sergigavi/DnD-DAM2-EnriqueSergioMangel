import {AfterViewInit, Component, Inject, OnInit, ViewChild} from '@angular/core';
import { NgForm } from '@angular/forms';
import { MatDialog, MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { MatPaginator } from '@angular/material/paginator';
import { MatSort } from '@angular/material/sort';
import { MatTableDataSource } from '@angular/material/table';
import { Router } from '@angular/router';
import { CookieService } from 'ngx-cookie-service';
import { AuthServiceService } from 'src/app/services/auth-user/auth-service.service';
import { EquipamientoAdminService } from 'src/app/services/equipamientoAdmin-service/equipamiento-admin.service';
import { EnumCatEquipo } from 'src/modelo/EnumCatEquipo';
import { EnumModificador } from 'src/modelo/EnumModificador';
import { EnumPropiedadEquipo } from 'src/modelo/EnumPropiedadEquipo';
import { EnumTipo } from 'src/modelo/EnumTipo';
import { IEquipo } from 'src/modelo/IEquipo';
import { DialogCrearEquipo } from './DialogCrearEquipo';

@Component({
  selector: 'app-equipamiento',
  templateUrl: './equipamiento.component.html',
  styleUrls: ['./equipamiento.component.css']
})


export class EquipamientoComponent implements OnInit,AfterViewInit   {
  title = 'Equipamiento';
  opened = false;
  equipo!:IEquipo
  idCurrentUser="";
  columnas:String[] = ["nombre","tipo","categoria","acceder","editar"]


  @ViewChild(MatPaginator,{static:true}) paginator! :MatPaginator
  @ViewChild(MatSort,{static:true}) sort!:MatSort

  dataSource = new MatTableDataSource<IEquipo>([]);

  constructor(private auth:AuthServiceService,private dialog : MatDialog,private equipamientoService: EquipamientoAdminService,private router:Router, private cookieService: CookieService){
  }

  getCurrenUser(){
    this.auth.data.subscribe((data:any)=>{
      this.idCurrentUser=data;
    })
  }

  ngOnInit(): void {
    this.getCurrenUser()
    if(this.idCurrentUser==null || this.idCurrentUser==""){
      this.router.navigate(['login'])
    }
    if(!this.cookieService.check("CurrentAdminId")){
      this.auth.deleteData()
      this.router.navigate(['/'])
    }
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
      this.showEquipo();
    });
  }

  public verEquipo(equipo:IEquipo){
    this.equipo=equipo
    const dialogRef=this.dialog.open(DialogVerEquipo,{
      data:{equipo:this.equipo},
      width:'100%'
    });

    dialogRef.afterClosed().subscribe((data:any)=>{
      this.showEquipo()
    });
  }

  public editarEquipo(equipo:IEquipo){
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

    tipos=Object.values(EnumTipo)
    categorias=Object.values(EnumCatEquipo)
    propiedades=Object.values(EnumPropiedadEquipo)
    modificadores=Object.values(EnumModificador)

    tipoElegido:string="";
    categoriaElegida:string="";
    propiedadElegida:string[]=[]
    modificadorElegido:string="";
    equipamiento!:IEquipo
    modificador:string=""


    onSubmit(equipoForm:NgForm){
      this.equipamiento=equipoForm.value
      this.equipamiento.idEquipo=this.equipo.idEquipo
      this.equipamiento.idString=this.equipo.idString
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
