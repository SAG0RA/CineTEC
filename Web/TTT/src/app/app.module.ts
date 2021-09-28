import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { MatSliderModule } from '@angular/material/slider';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';

//Componente
import { LoginComponent } from './components/components/login/login.component';
import { SharedModule } from './components/shared/shared.module';



@NgModule({

  declarations: [
    AppComponent,
    LoginComponent
  ],

  imports: [
    BrowserModule,
    MatSliderModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    SharedModule
  ],

  providers: [],
  bootstrap: [AppComponent]

})

export class AppModule { }


