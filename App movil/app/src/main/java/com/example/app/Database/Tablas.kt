package com.example.app.Database

class Tablas {
    abstract class Cliente{
        companion object{
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

    abstract class Cine{
        companion object{
            val TABLE_NAME = "Cines"
            val NOMBRE = "Nombre"
            val UBICACION = "Ubicacion"
            val CANTIDAD_SALAS = "Cantidad_salas"
        }
    }
}