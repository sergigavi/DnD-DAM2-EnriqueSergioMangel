import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { FormsModule } from '@angular/forms'
//import { HttpClientModule } from "@angular/common/http";  //  Para las peticiones http a la api
//import {MatSidenavModule} from '@angular/material/sidenav';
//import {MatBadgeModule} from '@angular/material/badge';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { UsuarioFormComponent } from './usuario-form/usuario-form.component';
//import { UsuarioServiceService } from './services/usuario-service/usuario-service.service';
//import { Usuario } from './modelo/usuario/usuario';

@NgModule({
  declarations: [
    AppComponent,
    UsuarioFormComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule
  ],
  //providers: [UsuarioServiceService],
  bootstrap: [AppComponent]
})
export class AppModule { }
