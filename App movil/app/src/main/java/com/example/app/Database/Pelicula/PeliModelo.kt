package com.example.app.Database.Pelicula

class PeliModelo {

    var nombre_original: String = ""
    var imagen: String = ""
    var duracion: String = ""
    var protagonistas: String = ""
    var director: String = ""
    var clasificacion: String = ""
    var proyecciones: String = ""

    constructor(nombre_original: String,imagen: String,duracion: String,protagonistas: String,director: String,clasificacion: String,proyecciones: String) {
        this.nombre_original = nombre_original
        this.imagen = imagen
        this.duracion = duracion
        this.protagonistas = protagonistas
        this.director = director
        this.clasificacion = clasificacion
        this.proyecciones = proyecciones
    }
    constructor() {
    }

    override fun toString(): String {
        return nombre_original
    }

}