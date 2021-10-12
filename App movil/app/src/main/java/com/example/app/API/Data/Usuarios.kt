package com.example.app.API.Data

import com.google.gson.annotations.SerializedName
// Clase encargada de administar los datos serializados de los clientes en el API
data class Usuarios (
    @SerializedName("id") val userID: String?,
    @SerializedName("fecha") val userDate: String?,
    @SerializedName("correo") val userEmail: String?,
    @SerializedName("p_apellido") val userFName: String?,
    @SerializedName("cedula") val userUID: Int?,
    @SerializedName("s_apellido") val userSName: String?,
    @SerializedName("nombre") val userName: String?,
    @SerializedName("contraseña") val userPassword: String?,
    @SerializedName("telefono") val userPhone: Int?
)
