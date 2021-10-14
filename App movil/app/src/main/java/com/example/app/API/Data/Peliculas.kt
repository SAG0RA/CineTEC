package com.example.app.API.Data

data class Peliculas(
    val nombreog: String,
    val nombre: String,
    val imagen: String,
    val duracion: String,
    val protagonistas: String,
    val director: String,
    val clasificacion: String,
    val proyecciones: List<String>
)