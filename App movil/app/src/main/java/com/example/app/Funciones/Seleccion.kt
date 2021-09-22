package com.example.app.Funciones

import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.app.API.Cuenta
import com.example.app.R
import kotlinx.android.synthetic.main.content_main.*
import kotlinx.android.synthetic.main.seleccion.*

class Seleccion : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.seleccion)

        btnsiguiente.setOnClickListener {
                startActivity(Intent(this, Asientos::class.java))
            }
    }

    /** Funcion encarcada de mostrar detalles de la cuenta del GET en pantalla
     *@param datos informacion del API
     * @param detalles pantalla para ver los detalles
     */
    fun verCuenta(datos: List<Cuenta>, detalles: TextView) {
        for (c in datos!!) {
            detalles.text = "Numero de cuenta: " + "${c.numero_cuenta}" +
                    "\n Descripcion: ${c.descripcion}" +
                    "\n Moneda: ${c.moneda}" +
                    "\n Tipo de cuenta: ${c.tipo_cuenta} "
        }
    }
}