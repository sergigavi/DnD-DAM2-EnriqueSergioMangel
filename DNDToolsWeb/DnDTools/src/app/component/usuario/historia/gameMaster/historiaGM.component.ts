import {Component, OnInit} from '@angular/core';
import { Router } from '@angular/router';
import { CookieService } from 'ngx-cookie-service';

@Component({
  selector: 'app-historiaGM',
  templateUrl: './historiaGM.component.html',
  styleUrls: ['./historiaGM.component.css']
})


export class HistoriaGMComponent implements OnInit  {
  title = 'Historia GM';
  opened = false;

  constructor(private router:Router, private cookieService:CookieService){}

  public navegar(ruta:String){
    this.router.navigate([`${ruta}`])
  }

  ngOnInit(): void {
    if(!this.cookieService.check("CurrentAdminId") && !this.cookieService.check("CurrentUserId")){
      this.router.navigate(['/'])
    }
  }

  toggleSidebar() {
    this.opened = !this.opened;
  }

}

