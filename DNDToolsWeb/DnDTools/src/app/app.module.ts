import { NgModule } from '@angular/core';
import { MatIconModule } from '@angular/material/icon';
import { MatToolbarModule } from '@angular/material/toolbar';
import { BrowserModule } from '@angular/platform-browser';
import { MatSidenavModule } from '@angular/material/sidenav';
import { RouterModule, Routes } from '@angular/router';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { MatSliderModule } from '@angular/material/slider'
import { MatMenuModule } from '@angular/material/menu'
import { MatButtonModule } from '@angular/material/button'
import { MatTableModule}  from '@angular/material/table'
import { MatPaginatorModule } from '@angular/material/paginator'
import {MatExpansionModule} from '@angular/material/expansion';
import { MatSortModule } from '@angular/material/sort';
import { MatCardModule } from '@angular/material/card';
import { MatGridListModule } from '@angular/material/grid-list';
import { MatInputModule } from '@angular/material/input';
import { MatDialogModule } from '@angular/material/dialog';
import {MatCheckboxModule} from '@angular/material/checkbox';
import {MatDatepickerModule} from '@angular/material/datepicker';
import { MatNativeDateModule } from '@angular/material/core';

//import { UsuarioServiceService } from './services/usuario-service/usuario-service.service';
//import { Usuario } from './modelo/usuario/usuario';
import { HttpClientModule } from "@angular/common/http";  //  Para las peticiones http a la api
import { MatSelectModule } from '@angular/material/select';

//import { CookieService } from 'ngx-cookie-service';

import { AppComponent } from './app.component';
import { UsuarioFormComponent } from './components/usuario-form/usuario-form.component';
import { UsuarioServiceService } from './services/usuario-service/usuario-service.service';

import { DialogCrearEquipo } from './component/admin/equipamiento/DialogCrearEquipo';
import { DialogCrearUsuario } from './component/admin/usuarios/dialogCrearUsuarios';
import { HomeComponent } from './component/home/home.component';
import { DialogEditarEquipo, DialogVerEquipo, EquipamientoComponent } from './component/admin/equipamiento/equipamiento.component';
import { PanelControlComponent } from './component/admin/panelControl/panelControl.component';
import { Dialog2Usuarios, DialogUsuarios, UsuariosComponent } from './component/admin/usuarios/usuarios.component';
import { PartidasUsuarioComponent } from './component/usuario/partidas/partidas.component';
import { InicioGMComponent } from './component/usuario/inicio/gameMaster/inicioGM.component';
import { InicioJugadorComponent } from './component/usuario/inicio/jugador/inicioJugador.component';
import { DialogMapasGM, DialogMapasGM2, MapasGMComponent } from './component/usuario/mapas/gameMaster/mapasGM.component';
import { DialogMapasJugador, MapasJugadorComponent } from './component/usuario/mapas/jugador/mapasJugador.component';
import { HistoriaGMComponent } from './component/usuario/historia/gameMaster/historiaGM.component';
import { HistoriaJugadorComponent } from './component/usuario/historia/jugador/historiaJugador.component';
import { Dialog2HeroesGM, DialogHeroesGM, HeroesGMComponent } from './component/usuario/heroes/gameMaster/heroresGM.component';
import { Dialog2HeroesJugador, DialogHeroesJugador, HeroesJugadorComponent } from './component/usuario/heroes/jugador/heroesJugador.component';
import { MatFormFieldModule } from '@angular/material/form-field';
import { PerfilComponent } from './component/perfil/perfil.component';
import { LPloginComponent } from './component/login/lplogin/lplogin.component';
import { EnumCatEquipo } from 'src/modelo/EnumCatEquipo';
import { DialogCrearFicha, DialogEditarFicha, DialogVerFicha, FichasAdmin } from './component/admin/fichas/fichasAdmin.component';
import { RegisterComponent } from './component/register/register/register.component';



const routes: Routes = [
  //landing page
  {path:'',component: HomeComponent},

  //login register
  {path:'login',component: LPloginComponent},
  {path:'register',component: RegisterComponent},

  //Inicio de sesion como admin->
  {path:'panelControl-admin',component: PanelControlComponent},

  //admins
  {path:'fichas-admin',component: FichasAdmin},
  {path:'equipamiento-admin',component: EquipamientoComponent},
  {path:'usuarios-admin',component: UsuariosComponent},

  //Inicio de  sesion como usuario->
  {path:'inicio',component: PartidasUsuarioComponent},

  //vistas de un usuario en una partida como jugador
  {path:'usuario-inicioJugador',component:InicioJugadorComponent},
  {path:'usuario-mapasJugador',component: MapasJugadorComponent},
  {path:'usuario-historiaJugador',component: HistoriaJugadorComponent},
  {path:'usuario-heroesJugador',component: HeroesJugadorComponent},

  //vistas de un usuario en una partida como GM
  {path:'usuario-inicioGM',component: InicioGMComponent},
  {path:'usuario-mapasGM',component: MapasGMComponent},
  {path:'usuario-historiaGM',component: HistoriaGMComponent},
  {path:'usuario-heroesGM',component: HeroesGMComponent},


  //perfil
  {path:'perfil',component: PerfilComponent},

];


@NgModule({
  declarations: [
    AppComponent,
    UsuarioFormComponent,
    HomeComponent,
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
    PerfilComponent,
    DialogMapasJugador,
    DialogMapasGM,
    DialogMapasGM2,
    DialogHeroesJugador,
    Dialog2HeroesJugador,
    DialogHeroesGM,
    Dialog2HeroesGM,
    DialogUsuarios,
    Dialog2Usuarios,
    DialogCrearEquipo,
    LPloginComponent,
    DialogVerEquipo,
    DialogEditarEquipo,
    FichasAdmin,
    DialogCrearFicha,
    DialogVerFicha,
    DialogEditarFicha,
    DialogCrearUsuario,
    RegisterComponent

  ],
  imports: [
    BrowserModule,
    RouterModule.forRoot(routes),
    FormsModule,
    HttpClientModule,
    MatIconModule,
    MatSidenavModule,
    MatToolbarModule,
    BrowserAnimationsModule,
    MatSliderModule,
    MatMenuModule,
    MatButtonModule,
    MatTableModule,
    MatPaginatorModule,
    MatSortModule,
    MatFormFieldModule,
    MatCardModule,
    MatInputModule,
    MatDialogModule,
    MatSelectModule,
    ReactiveFormsModule,
    MatGridListModule,
    MatExpansionModule,
    MatDatepickerModule,
    MatNativeDateModule,
    MatCheckboxModule
  ],
  entryComponents: [UsuarioFormComponent],
  providers: [MatDatepickerModule,/*CookieService*/],
  bootstrap: [AppComponent]
})
export class AppModule {}
