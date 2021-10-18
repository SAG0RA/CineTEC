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

        val usuario = intent.getStringExtra("Usuario")
        /////////////// BASE DE DATOS SQLite (Cines) //////////////
        cinedb = CinesDB(this)
        peliculadb = PeliculasDB(this)
        val cines_registrados = cinedb!!.getData()
        val peliculas_registradas = peliculadb!!.getPeliculas()
        val proyecciones_registradas = peliculadb!!.getProyecciones()
        val imagenes_registradas = peliculadb!!.getImagen()

        val duracion_registrada = peliculadb!!.getDuracion()
        val director_registrado = peliculadb!!.getDirector()
        val protagonista_registrado = peliculadb!!.getProtagonista()
        val clasificacion_registrada = peliculadb!!.getClasificacion()
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

                //////////////////////////////// DETALLES //////////////////////////////////////////

                val duracion = findViewById<TextView>(R.id.lblduracion)
                val director = findViewById<TextView>(R.id.lbldirector)
                val protagonista = findViewById<TextView>(R.id.lblprotagonista)
                val clasificacion = findViewById<TextView>(R.id.lblclasificacion)

                val duracion_seleccionada = duracion_registrada[position]
                val director_seleccionado = director_registrado[position]
                val protagonista_seleccionado = protagonista_registrado[position]
                val clasificacion_seleccionada = clasificacion_registrada[position]

                val duracion_seleccionada_string = duracion_seleccionada.duracion
                val director_seleccionado_string = director_seleccionado.director
                val protagonista_seleccionado_string = protagonista_seleccionado.protagonistas
                val clasificacion_seleccionada_string = clasificacion_seleccionada.clasificacion

                duracion.text = duracion_seleccionada_string
                director.text = director_seleccionado_string
                protagonista.text = protagonista_seleccionado_string
                clasificacion.text = clasificacion_seleccionada_string

            }

        }
        btnsiguiente.setOnClickListener {
                startActivity(Intent(this, Boletos::class.java)
                    .putExtra("Usuario",usuario))
            }
    }

}