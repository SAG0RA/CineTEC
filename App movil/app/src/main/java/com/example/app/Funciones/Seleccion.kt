package com.example.app.Funciones

import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.appcompat.app.AppCompatActivity
import com.example.app.Database.Cine.CinesDB
import com.example.app.Database.Cliente.ClientesDB
import com.example.app.R
import kotlinx.android.synthetic.main.seleccion.*

class Seleccion : AppCompatActivity() {

    private var cinedb:CinesDB? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.seleccion)

        /////////////// BASE DE DATOS SQLite (Cines) //////////////
        cinedb = CinesDB(this)
        val cines_registrados = cinedb!!.getData()
        //////////////////////////////////////////////////////////////

        val spinner_cines = findViewById<Spinner>(R.id.spinner_cines)
        val adaptador = ArrayAdapter(this, android.R.layout.simple_spinner_item,cines_registrados)
        spinner_cines.adapter = adaptador


        btnsiguiente.setOnClickListener {
                startActivity(Intent(this, Asientos::class.java))
            }
    }

}