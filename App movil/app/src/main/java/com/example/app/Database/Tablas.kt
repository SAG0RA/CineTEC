package com.example.app.Database

class Tablas {
    abstract class Cliente{
        companion object{
            val TABLE_NAME = "Clientes"
            val NOMBRE = "Nombre"
            val CEDULA = "Cedula"
            val EDAD = "Edad"
            val FECHA_NAC = "Fecha de nacimiento"
            val USUARIO = "Usuario"
            val CONTRASENA = "Contrasena"
        }
    }
}