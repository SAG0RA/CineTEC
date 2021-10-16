package com.example.app.Funciones

import android.content.Intent
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.example.app.R
import kotlinx.android.synthetic.main.boletos.*
import kotlinx.android.synthetic.main.seleccion.*

class Boletos : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.boletos)
        
        var precio_menores = 0
        var precio_adultos = 0

        var cantidad_menores = 0
        var cantidad_adultos = 0

        val menores = findViewById<TextView>(R.id.lblmenorcantidad)
        val adultos = findViewById<TextView>(R.id.lbladultocantidad)
        val total = findViewById<TextView>(R.id.lbltotal)
        val input_tarjeta = findViewById<EditText>(R.id.input_tarjeta) as EditText

        val tarjetas = listOf<String>("Mastercard", "Visa", "BAC Credomatic")
        val spinner_tarjetas = findViewById<Spinner>(R.id.spinner_tarjeta)
        val adaptador = ArrayAdapter(this, android.R.layout.simple_spinner_item, tarjetas)
        spinner_tarjetas.adapter = adaptador

        /////////////////////////////////////////////////////////////////////////////////////
        btnsumar1.setOnClickListener {
            cantidad_menores += 1
            menores.text = cantidad_menores.toString()
            precio_menores += 2500
            val precio_total = precio_menores + precio_adultos
            total.text = precio_total.toString()
        }

        btnrestar1.setOnClickListener {
            if (cantidad_menores == 0) {
                Toast.makeText(
                    this, "Agregue menores de edad",
                    Toast.LENGTH_LONG
                ).show()
                cantidad_menores = 0
            } else {
                cantidad_menores -= 1
                menores.text = cantidad_menores.toString()
                precio_menores -= 2500
                val precio_total = precio_menores + precio_adultos
                total.text = precio_total.toString()

            }
        }
        /////////////////////////////////////////////////////////////////////////////////////
        btnsumar2.setOnClickListener {
            cantidad_adultos += 1
            precio_adultos += 3500
            adultos.text = cantidad_adultos.toString()
            val precio_total = precio_menores + precio_adultos
            total.text = precio_total.toString()
        }

        btnrestar2.setOnClickListener {
            if (cantidad_adultos == 0) {
                Toast.makeText(
                    this, "Agregue adultos",
                    Toast.LENGTH_LONG
                ).show()
                cantidad_adultos = 0
            } else {
                cantidad_adultos -= 1
                adultos.text = cantidad_adultos.toString()
                precio_adultos -= 3500
                val precio_total = precio_menores + precio_adultos
                total.text = precio_total.toString()
            }
        }
        //////////////////////////////////////////////////////////////////////////////////////

        btnsiguiente2.setOnClickListener{
            if(total.text == "0" || input_tarjeta.toString() == ""){
                Toast.makeText(
                    this, "Agregue sus boletos y sus datos",
                    Toast.LENGTH_LONG
                ).show()
            }else{
                startActivity(Intent(this, Asientos::class.java))
            }
        }
    }
}