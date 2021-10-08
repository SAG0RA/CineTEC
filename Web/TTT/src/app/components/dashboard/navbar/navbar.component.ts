import { Component, OnInit } from '@angular/core';
import { Menu } from 'src/app/interfaces/menu';
import { MenuService } from 'src/app/services/menu.service';

//EN ESTE COMPONENTE HAGO QUE EL SERVICIO CREADO SEA COSUMIBLE

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css']
})
export class NavbarComponent implements OnInit {

  menu: Menu[] = [] //ARRAY

  constructor(private _menuService: MenuService) { }

  ngOnInit(): void {
    this.cargarMenu();
  }

  cargarMenu(){
    this._menuService.getMenu().subscribe(data =>{ //En daahboard<inspec<console puedo ver que estoy consumiendo
      //console.log(data);
      this.menu = data;
    })
  }

}


