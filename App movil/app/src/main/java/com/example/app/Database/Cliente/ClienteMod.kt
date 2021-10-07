package com.example.app.Database.Cliente

class ClienteMod {

     var cedula: Int = 0
     var nombre: String = ""
     var apellido: String = ""
     var edad: Int = 0
     var fecha_nac: String = ""
     var usuario: String = ""
     var contrasena: String = ""

    constructor(cedula: Int,nombre: String,apellido: String, edad: Int, fecha_nac: String,usuario: String,contrasena: String) {
        this.cedula = cedula
        this.nombre = nombre
        this.apellido = apellido
        this.edad = edad
        this.fecha_nac = fecha_nac
        this.usuario = usuario
        this.contrasena = contrasena
    }
}
