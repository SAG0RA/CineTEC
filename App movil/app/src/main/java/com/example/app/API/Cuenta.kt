package com.example.app.API
// Clase encargada de administar los datos serializados de la cuenta en el API
data class Cuenta(
    val numero_cuenta: String,
    val descripcion: String,
    val moneda: String,
    val tipo_cuenta: String,
    val acliente: String
)