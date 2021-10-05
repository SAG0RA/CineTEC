package com.example.app.API
// Clase encargada de administar los datos serializados de la cuenta en el API
data class Cliente(
    val cedula: Int,
    val pnombre: String,
    val snombre: String,
    val apellido: String,
    val telefono: Int,
    val fechanac: String,
    val edad: Int,
    val usuario: String,
    val contraseña: String
)