import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { FormsModule } from '@angular/forms'
import { HttpClientModule } from "@angular/common/http";  //  Para las peticiones http a la api
import {MatSidenavModule} from '@angular/material/sidenav';
//import {MatBadgeModule} from '@angular/material/badge';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { UsuarioFormComponent } from './components/usuario-form/usuario-form.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { MatSliderModule } from '@angular/material/slider'
import { MatMenuModule } from '@angular/material/menu'
import { MatButtonModule } from '@angular/material/button'
import { MatTableModule}  from '@angular/material/table'
import { MatPaginatorModule } from '@angular/material/paginator'
//import { UsuarioServiceService } from './services/usuario-service/usuario-service.service';
//import { Usuario } from './modelo/usuario/usuario';

@NgModule({
  declarations: [
    AppComponent,
    UsuarioFormComponent,

  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    HttpClientModule,
    BrowserAnimationsModule,
    MatSliderModule,
    MatMenuModule,
    MatButtonModule,
    MatTableModule,
    MatPaginatorModule,
    MatSidenavModule
  ],
  entryComponents: [UsuarioFormComponent],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
