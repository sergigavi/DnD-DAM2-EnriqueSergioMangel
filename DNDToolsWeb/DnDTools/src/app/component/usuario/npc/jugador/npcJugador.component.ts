import {Component, OnInit, ViewChild} from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { MatPaginator } from '@angular/material/paginator';
import { MatTableDataSource } from '@angular/material/table';

export interface PeriodicElement {
  nombreNpc: string;
}

const ELEMENT_DATA: PeriodicElement[] = [
  {nombreNpc: 'Martinez'},
  {nombreNpc: 'Goblin'},
  {nombreNpc: 'Dragon Anciano'},
  {nombreNpc: 'Troll'},
  {nombreNpc: 'Sirena'},
  {nombreNpc: 'Esqueleto'},
  {nombreNpc: 'Gigante'},
  {nombreNpc: 'Unicornio'},
  {nombreNpc: 'Troll de las Nieves'},
  {nombreNpc: 'Dragon Negro'},
];

@Component({
  selector: 'app-npcJugador',
  templateUrl: './npcJugador.component.html',
  styleUrls: ['./npcJugador.component.css']
})


export class NpcJugadorComponent implements OnInit  {
  title = 'Npc Jugador';
  opened = false;

  displayedColumns: string[] = ['nombreNpc','acceder'];
  dataSource = new MatTableDataSource<PeriodicElement>(ELEMENT_DATA);

  constructor(public dialog: MatDialog){}

  openDialog() {
    const dialogRef = this.dialog.open(DialogNpcJugador);

    dialogRef.afterClosed().subscribe(result => {
      console.log(`Dialog result: ${result}`);
    });
  }

  @ViewChild('matPaginator',{static:true}) paginator!:MatPaginator

  ngOnInit(): void {
  }

  ngAfterViewInit(){
    this.dataSource.paginator=this.paginator;
  }

  toggleSidebar() {
    this.opened = !this.opened;
  }

}

@Component({
  selector: 'npcJugadorDialog',
  templateUrl: 'npcJugadorDialog.html'
})

export class DialogNpcJugador {}

