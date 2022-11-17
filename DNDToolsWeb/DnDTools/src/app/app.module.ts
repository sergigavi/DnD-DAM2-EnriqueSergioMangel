import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { RouterModule, Routes } from '@angular/router';
import { FormsModule } from '@angular/forms';

import { HttpClientModule } from "@angular/common/http";  //  Para las peticiones http a la api
import { MatSidenavModule } from '@angular/material/sidenav';
//import {MatBadgeModule} from '@angular/material/badge';

import { AppComponent } from './app.component';
import { UsuarioFormComponent } from './components/usuario-form/usuario-form.component';
import { UsuarioServiceService } from './services/usuario-service/usuario-service.service';

import { HomeComponent } from './component/home/home.component';
import { PartidasComponent } from './component/admin/partidas/partidas.component';
import { EquipamientoComponent } from './component/admin/equipamiento/equipamiento.component';
import { PanelControlComponent } from './component/admin/panelControl/panelControl.component';
import { UsuariosComponent } from './component/admin/usuarios/usuarios.component';
import { PartidasUsuarioComponent } from './component/usuario/partidas/partidas.component';
import { InicioGMComponent } from './component/usuario/inicio/gameMaster/inicioGM.component';
import { InicioJugadorComponent } from './component/usuario/inicio/jugador/inicioJugador.component';
import { MapasGMComponent } from './component/usuario/mapas/gameMaster/mapasGM.component';
import { MapasJugadorComponent } from './component/usuario/mapas/jugador/mapasJugador.component';
import { HistoriaGMComponent } from './component/usuario/historia/gameMaster/historiaGM.component';
import { HistoriaJugadorComponent } from './component/usuario/historia/jugador/historiaJugador.component';
import { HeroesGMComponent } from './component/usuario/heroes/gameMaster/heroresGM.component';
import { HeroesJugadorComponent } from './component/usuario/heroes/jugador/heroesJugador.component';
import { NpcGMComponent } from './component/usuario/npc/gameMaster/npcGM.component';
import { NpcJugadorComponent } from './component/usuario/npc/jugador/npcJugador.component';
PartidasUsuarioComponent

const routes: Routes = [
  //invitados
  {path:'',component: HomeComponent},
  //ambos
  {path:'usuario-partidasUsuario',component: PartidasUsuarioComponent},
  //admins
  {path:'partidas-admin',component: PartidasComponent},
  {path:'equipamiento-admin',component: EquipamientoComponent},
  {path:'panelControl-admin',component: PanelControlComponent},
  {path:'usuarios-admin',component: UsuariosComponent},
  //usuarios

  {path:'usuario-inicioJugador',component:InicioJugadorComponent},
  {path:'usuario-inicioGM',component: InicioGMComponent},
  {path:'usuario-mapasJugador',component: MapasJugadorComponent},
  {path:'usuario-mapasGM',component: MapasGMComponent},
  {path:'usuario-historiaJugador',component: HistoriaJugadorComponent},
  {path:'usuario-historiaGM',component: HistoriaGMComponent},
  {path:'usuario-heroesJugador',component: HeroesJugadorComponent},
  {path:'usuario-heroesGM',component: HeroesGMComponent},
  {path:'usuario-npcJugador',component: NpcJugadorComponent},
  {path:'usuario-npcGM',component: NpcGMComponent},


];

@NgModule({
  declarations: [
    AppComponent,
    UsuarioFormComponent,
    HomeComponent,
    PartidasComponent,
    EquipamientoComponent,
    PanelControlComponent,
    UsuariosComponent,
    PartidasUsuarioComponent,
    InicioJugadorComponent,
    InicioGMComponent,
    MapasJugadorComponent,
    MapasGMComponent,
    HistoriaGMComponent,
    HistoriaJugadorComponent,
    HeroesJugadorComponent,
    HeroesGMComponent,
    NpcJugadorComponent,
    NpcGMComponent

  ],
  imports: [
    BrowserModule,
    RouterModule.forRoot(routes),
    FormsModule,
    HttpClientModule,
    //MatSidenavModule,
  ],
  providers: [UsuarioServiceService],
  bootstrap: [AppComponent]
})
export class AppModule { }
