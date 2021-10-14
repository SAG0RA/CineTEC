package com.example.app.Funciones

import RestAPIService
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import com.example.app.API.Data.Cines
import com.example.app.API.Data.Clientes
import com.example.app.API.Data.Peliculas
import com.example.app.Database.Cine.CineModelo
import com.example.app.Database.Cine.CinesDB
import com.example.app.Database.Cliente.ClienteModelo
import com.example.app.Database.Cliente.ClientesDB
import com.example.app.Database.Pelicula.PeliModelo
import com.example.app.Database.Pelicula.PeliculasDB
import com.example.app.R

class Inicio : AppCompatActivity() {

    lateinit var handler: Handler
    private var clientedb: ClientesDB? = null
    private var cinedb: CinesDB? = null
    private var peliculadb: PeliculasDB? = null
    

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.inicio)

        //////////// SINCRONIZACION CON BASE DE DATOS ///////////
        clientedb = ClientesDB(this)
        cinedb = CinesDB(this)
        peliculadb = PeliculasDB(this)

        val API = RestAPIService()
        API.getClient(clientedb!!)
        API.getCine(cinedb!!)
        API.getPelicula(peliculadb!!)
        /////////////////////////////////////////////////////////

        handler = Handler()
        handler.postDelayed({

            val intent = Intent(this, Login::class.java)
            startActivity(intent)
            finish()
        }, 3000)
    }


    ///////////////////////////////////////////////
    /////////////// SINCRONIZACION ////////////////
    ///////////////////////////////////////////////

    fun sync_Cliente(datos: List<Clientes>, db: ClientesDB) {
        for (c in datos) {
            val carga_cliente = ClienteModelo(
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

    fun sync_Cines(datos: List<Cines>, db: CinesDB) {
        for (c in datos) {
            val carga_cine = CineModelo(
                c.cantidadsalas,
                c.nombrecine,
                c.ubicacion
            )
            db.insert(carga_cine)
        }
    }

    fun sync_Peliculas(datos: List<Peliculas>, db: PeliculasDB) {
        for (c in datos) {
            val union = c.proyecciones.joinToString(",")
            val carga_cine = PeliModelo(
                c.nombreog,
                c.imagen,
                c.duracion,
                c.protagonistas,
                c.director,
                c.clasificacion,
                union
            )
            db.insert(carga_cine)
        }
    }

}