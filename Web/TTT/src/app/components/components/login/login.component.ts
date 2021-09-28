import { IfStmt } from '@angular/compiler';
import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Router } from '@angular/router';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  form: FormGroup;
  loading = false;

  constructor(private fb: FormBuilder, private _snackBar: MatSnackBar, private router: Router) {
    this.form = this.fb.group({
      usuario:['', Validators.required],
      password:['',Validators.required]
    })
   }

  ngOnInit(): void {
  }

  ingresar(){
    console.log(this.form);
    const usuario = this.form.value.usuario;
    const password = this.form.value.password;

    console.log(usuario);
    console.log(password);

  /**esta funcion dada que no posseo el backend de verificacion del usuario (Api) */

    if(usuario == 'emorales' && password == 'admin'){
      this.fakeLoading();
    }else{
      this.error();
      this.form.reset();
    }

  }

  error(){
    this._snackBar.open('Usuario o contraseña incorrecto','', {
      duration: 5000,
      horizontalPosition: 'center',
      verticalPosition: 'bottom'
    })
  }

  fakeLoading(){
    this.loading = true;
    setTimeout(() =>{
      //falta ruta
      this.router.navigate(['dashboard']);

    }, 1500);
  }

}
