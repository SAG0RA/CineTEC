package com.example.app.Database

import com.example.app.Database.Cliente.ClienteMod

class Tablas {
    abstract class Cliente{
        companion object{
            val _ID = "_id"
            val TABLE_NAME = "Clientes"
            val NOMBRE = "Nombre"
            val APELLIDO = "Apellido"
            val CEDULA = "Cedula"
            val EDAD = "Edad"
            val FECHA_NAC = "Fecha_nacimiento"
            val USUARIO = "Usuario"
            val CONTRASENA = "Contrasena"
        }
    }
}