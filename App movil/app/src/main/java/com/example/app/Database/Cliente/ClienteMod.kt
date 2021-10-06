package com.example.app.Database.Cliente

class ClienteMod {

    private var cedula: Int = 0
    private var nombre: String = ""
    private var edad: Int = 0
    private var fecha_nac: String = ""
    private var usuario: String = ""
    private var contrasena: String = ""


    constructor(cedula: Int,nombre: String, edad: Int, fecha_nac: String,usuario: String,contrasena: String) {
        this.cedula = cedula
        this.nombre = nombre
        this.edad = edad
        this.fecha_nac = fecha_nac
        this.usuario = usuario
        this.contrasena = contrasena
    }

    fun getCedula(): Int{
        return  cedula;
    }
    fun getNombre():String{
        return nombre;
    }
    fun getEdad():Int{
        return edad;
    }
    fun getFecha_nac():String{
        return fecha_nac;
    }

    fun getUsuario():String{
        return usuario;
    }

    fun getContrasena():String{
        return contrasena;
    }

    override fun toString(): String {
        return usuario
    }

}
