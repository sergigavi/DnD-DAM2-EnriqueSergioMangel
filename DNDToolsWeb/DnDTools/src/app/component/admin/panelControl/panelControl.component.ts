import {Component, OnInit} from '@angular/core';
import { Router } from '@angular/router';
import { EquipamientoAdminService } from 'src/app/services/equipamientoAdmin-service/equipamiento-admin.service';
import { FichaAdminService } from 'src/app/services/fichaPersonaje-service/ficha-admin.service';
import { UsuarioServiceService } from 'src/app/services/usuario-service/usuario-service.service';

@Component({
  selector: 'app-panelControl',
  templateUrl: './panelControl.component.html',
  styleUrls: ['./panelControl.component.css']
})


export class PanelControlComponent implements OnInit  {
  title = 'PanelControl';
  opened = false;
  usuario=0;
  equipamiento=0;
  fichas=0;

  constructor(private router:Router,private usuarioServicio: UsuarioServiceService,private equipamientoService: EquipamientoAdminService,
    private fichaService:FichaAdminService){}

  ngOnInit(): void {
    this.usuarioServicio.getCantidad().subscribe((data:any)=>{
      this.usuario=data
    })

    this.equipamientoService.getCantidad().subscribe((data:any)=>{
      this.equipamiento=data
    })

    this.fichaService.getCantidad().subscribe((data:any)=>{
      this.fichas=data
    })
  }

  toggleSidebar() {
    this.opened = !this.opened;
  }

  public navegar(ruta:String){
    this.router.navigate([`${ruta}`])
  }

}

