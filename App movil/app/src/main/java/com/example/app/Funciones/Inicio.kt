package com.example.app.Funciones

import RestAPIService
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.app.API.Clientes
import com.example.app.Database.Cliente.ClienteMod
import com.example.app.Database.Cliente.ClientesDB
import com.example.app.Database.Tablas
import com.example.app.R

class Inicio : AppCompatActivity() {

    lateinit var handler: Handler
    private var db: ClientesDB? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.inicio)
        ///////////////////
        db = ClientesDB(this)
        val API = RestAPIService()
        API.getClient(db!!)
        ///////////////////
        handler = Handler()
        handler.postDelayed({

            val intent = Intent(this, Login::class.java)
            startActivity(intent)
            finish()
        }, 3000)
    }

    fun cargarDatos(datos: List<Clientes>, db: ClientesDB) {
        for (c in datos) {
            val carga_cliente = ClienteMod(
                c.cedula,
                c.pnombre,
                c.apellido,
                c.edad,
                c.fechanac,
                c.usuario,
                c.contraseña
            )
            db.insert(carga_cliente)
        }
    }

}