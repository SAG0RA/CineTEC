import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Menu } from '../interfaces/menu';

//servicio de conexion http
//AQUI CREO UN PRIMER GET EN CONEXION LOCAL


@Injectable({
  providedIn: 'root'
})
export class MenuService {

  constructor(private http: HttpClient) { }

  getMenu(): Observable<Menu[]>{ // aca especifico que estoy jalando un array de menus
    return this.http.get<Menu[]>('./assets/data/menu.json') //ACA CONSUMO MI RECURSO LOCAL CON GET (min 1:11:40)
  }
}


//En el minuto 1:10:00 se habla del backend que va desde aca
