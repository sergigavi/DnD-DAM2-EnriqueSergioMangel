import {AfterViewInit, Component, OnInit, ViewChild} from '@angular/core';
import { PartidasService } from 'src/app/services/partidas.service';
import {MatPaginator} from '@angular/material/paginator';
import {MatTableDataSource} from '@angular/material/table';

/**
 * @title Data table with sorting, pagination, and filtering.
 */
@Component({
  selector: 'app-partidas',
  templateUrl: './partidas.component.html',
  styleUrls: ['./partidas.component.css']
})


export class PartidasComponent implements OnInit  {
  title = 'Partidas';
  opened = false;

  ngOnInit(): void {
  }

  toggleSidebar() {
    this.opened = !this.opened;
  }

}

