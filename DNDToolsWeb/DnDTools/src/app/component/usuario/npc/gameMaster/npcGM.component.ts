<<<<<<< HEAD
import {Component, OnInit} from '@angular/core';
=======
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
>>>>>>> main

@Component({
  selector: 'app-npcGM',
  templateUrl: './npcGM.component.html',
  styleUrls: ['./npcGM.component.css']
})


export class NpcGMComponent implements OnInit  {
  title = 'Npc GM';
  opened = false;

<<<<<<< HEAD
  constructor(){}
=======
  displayedColumns: string[] = ['nombreNpc','acceder','editar'];
  dataSource = new MatTableDataSource<PeriodicElement>(ELEMENT_DATA);

  constructor(public dialog: MatDialog){}

  openDialog() {
    const dialogRef = this.dialog.open(DialogNpcGM);

    dialogRef.afterClosed().subscribe(result => {
      console.log(`Dialog result: ${result}`);
    });
  }

  openDialog2() {
    const dialogRef = this.dialog.open(Dialog2NpcGM);

    dialogRef.afterClosed().subscribe(result => {
      console.log(`Dialog result: ${result}`);
    });
  }

  @ViewChild('matPaginator',{static:true}) paginator!:MatPaginator
>>>>>>> main

  ngOnInit(): void {
  }

  toggleSidebar() {
    this.opened = !this.opened;
  }

}

<<<<<<< HEAD
=======
@Component({
  selector: 'npcGMDialog',
  templateUrl: 'npcGMDialog.html'
})

export class DialogNpcGM {}

@Component({
  selector: 'npcGMDialog2',
  templateUrl: 'npcGMDialog2.html'
})

export class Dialog2NpcGM {}

>>>>>>> main
