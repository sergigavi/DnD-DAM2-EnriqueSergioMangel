import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { FormsModule } from '@angular/forms'
import { HttpClientModule } from "@angular/common/http";  //  Para las peticiones http a la api
//import {MatSidenavModule} from '@angular/material/sidenav';
//import {MatBadgeModule} from '@angular/material/badge';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { UsuarioComponent } from './usuario/usuario.component';
import { UsuarioFormComponent } from './usuario-form/usuario-form.component';
import { UsuarioServiceService } from './usuario-service/usuario-service.service';

@NgModule({
  declarations: [
    AppComponent,
    UsuarioComponent,
    UsuarioFormComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule
  ],
  providers: [UsuarioServiceService],
  bootstrap: [AppComponent]
})
export class AppModule { }
