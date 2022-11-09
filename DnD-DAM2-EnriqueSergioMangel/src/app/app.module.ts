import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { RouterModule, Routes } from '@angular/router';
import { SidebarModule } from 'ng-sidebar';

import { AppComponent } from './app.component';
import { HomeComponent } from './component/home/home.component';
import { PartidasComponent } from './component/admin/partidas/partidas.component';
import { EquipamientoComponent } from './component/admin/equipamiento/equipamiento.component';
import { PanelControlComponent } from './component/admin/panelControl/panelControl.component';
import { UsuariosComponent } from './component/admin/usuarios/usuarios.component';

const routes: Routes = [

  {path:'',component: HomeComponent},
  {path:'partidas-admin',component: PartidasComponent},
  {path:'equipamiento-admin',component: EquipamientoComponent},
  {path:'panelControl-admin',component: PanelControlComponent},
  {path:'usuarios-admin',component: UsuariosComponent},

];

@NgModule({
  declarations: [
    AppComponent,
    HomeComponent,
    PartidasComponent,
    EquipamientoComponent,
    PanelControlComponent,
    UsuariosComponent,
  ],
  imports: [
    BrowserModule,
    SidebarModule.forRoot(),
    RouterModule.forRoot(routes),
  ],
  providers: [],
  bootstrap: [AppComponent]
})

export class AppModule { }
