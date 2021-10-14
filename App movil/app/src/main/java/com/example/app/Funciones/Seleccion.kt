package com.example.app.Funciones

import android.content.Intent
import android.content.res.Resources
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.example.app.Database.Cine.CinesDB
import com.example.app.Database.Pelicula.PeliculasDB
import com.example.app.R
import kotlinx.android.synthetic.main.seleccion.*

class Seleccion : AppCompatActivity(){

    private var cinedb:CinesDB? = null
    private var peliculadb:PeliculasDB? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.seleccion)

        /////////////// BASE DE DATOS SQLite (Cines) //////////////
        cinedb = CinesDB(this)
        peliculadb = PeliculasDB(this)
        val cines_registrados = cinedb!!.getData()
        val peliculas_registradas = peliculadb!!.getPeliculas()
        val proyecciones_registradas = peliculadb!!.getProyecciones()
        val imagenes_registradas = peliculadb!!.getImagen()
        //////////////////////////////////////////////////////////////

        /////////////////// SPINNER CINES ////////////////////////////
        val spinner_cines = findViewById<Spinner>(R.id.spinner_cines)
        val adaptador_cines = ArrayAdapter(this, android.R.layout.simple_spinner_item,cines_registrados)
        spinner_cines.adapter = adaptador_cines

        ////////////////// SPINNER PELICULAS ////////////////////////
        val spinner_peliculas = findViewById<Spinner>(R.id.spinner_peliculas)
        val adaptador_peliculas = ArrayAdapter(this, android.R.layout.simple_spinner_item,peliculas_registradas)
        spinner_peliculas.adapter = adaptador_peliculas

        spinner_peliculas.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(parent: AdapterView<*>?) {

            }

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {

                /////////////////////////////// SPINNER PROYECCIONES ////////////////////////////////
                val proyecciones_seleccionadas = proyecciones_registradas[position]
                val proyecciones_seleccionadas_string = proyecciones_seleccionadas.proyecciones
                val proyecciones_seleccionadas_list = proyecciones_seleccionadas_string.split(",")

                val spinner_proyecciones = findViewById<Spinner>(R.id.spinner_proyecciones)
                val adaptador_proyecciones = ArrayAdapter(applicationContext, android.R.layout.simple_spinner_item,proyecciones_seleccionadas_list)
                spinner_proyecciones.adapter = adaptador_proyecciones


                ///////////////////////////////// IMAGEN PELICULA //////////////////////////////////
                val IMAGEN = findViewById<ImageView>(R.id.imagen_pelicula)
                val imagenes_seleccionadas = imagenes_registradas[position]
                val imagenes_seleccionadas_string = imagenes_seleccionadas.imagen

                val recurso: Resources = resources
                val ID = recurso.getIdentifier(imagenes_seleccionadas_string,"drawable",packageName)

                IMAGEN.setImageResource(ID)

            }

        }


        btnsiguiente.setOnClickListener {
                startActivity(Intent(this, Asientos::class.java))
            }
    }

}