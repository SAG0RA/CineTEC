package com.example.app.Database.Cine

class CineModelo {
    var nombre_cine: String = ""
    var ubicacion: String = ""
    var cantidad_salas: Int = 0

    constructor(cantidad_salas: Int,nombre_cine: String,ubicacion: String) {
        this.cantidad_salas = cantidad_salas
        this.nombre_cine = nombre_cine
        this.ubicacion = ubicacion

    }
    constructor() {
    }

    override fun toString(): String {
        return nombre_cine
    }
}