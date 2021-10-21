package com.example.app.Funciones

import android.content.Intent
import android.content.res.Resources
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.example.app.R
import kotlinx.android.synthetic.main.asientos.*

class Asientos : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.asientos)

        val menores = intent.getIntExtra("Menores", 0)
        val adultos = intent.getIntExtra("Adultos", 0)
        val monto = intent.getIntExtra("Monto", 0)
        val usuario = intent.getStringExtra("Usuario")
        var total = menores + adultos
        var puestos_disponibles = total
        var counter = 0
        puestos.text = puestos_disponibles.toString()
        ////////////////////////////////////////// ASIENTOS ///////////////////////////////////////
        sillas1.setOnClickListener {
            counter += 1
            if (counter <= total) {
                val recurso: Resources = resources
                val ID = recurso.getIdentifier("sillaazul", "drawable", packageName)
                sillas1.setImageResource(ID)
                puestos_disponibles -= 1
                puestos.text = puestos_disponibles.toString()
            }
        }
        sillas2.setOnClickListener {
            counter += 1
            if (counter <= total) {
                val recurso: Resources = resources
                val ID = recurso.getIdentifier("sillaazul", "drawable", packageName)
                sillas2.setImageResource(ID)
                puestos_disponibles -= 1
                puestos.text = puestos_disponibles.toString()
            }
        }
        sillas3.setOnClickListener {
            counter += 1
            if (counter <= total) {
                val recurso: Resources = resources
                val ID = recurso.getIdentifier("sillaazul", "drawable", packageName)
                sillas3.setImageResource(ID)
                puestos_disponibles -= 1
                puestos.text = puestos_disponibles.toString()
            }
        }
        sillas4.setOnClickListener {
            counter += 1
            if (counter <= total) {
                val recurso: Resources = resources
                val ID = recurso.getIdentifier("sillaazul", "drawable", packageName)
                sillas4.setImageResource(ID)
                puestos_disponibles -= 1
                puestos.text = puestos_disponibles.toString()
            }
        }
        sillas5.setOnClickListener {
            counter += 1
            if (counter <= total) {
                val recurso: Resources = resources
                val ID = recurso.getIdentifier("sillaazul", "drawable", packageName)
                sillas5.setImageResource(ID)
                puestos_disponibles -= 1
                puestos.text = puestos_disponibles.toString()
            }
        }
        sillas6.setOnClickListener {
            counter += 1
            if (counter <= total) {
                val recurso: Resources = resources
                val ID = recurso.getIdentifier("sillaazul", "drawable", packageName)
                sillas6.setImageResource(ID)
                puestos_disponibles -= 1
                puestos.text = puestos_disponibles.toString()
            }
        }
        sillas7.setOnClickListener {
            counter += 1
            if (counter <= total) {
                val recurso: Resources = resources
                val ID = recurso.getIdentifier("sillaazul", "drawable", packageName)
                sillas7.setImageResource(ID)
                puestos_disponibles -= 1
                puestos.text = puestos_disponibles.toString()
            }
        }
        sillas8.setOnClickListener {
            counter += 1
            if (counter <= total) {
                val recurso: Resources = resources
                val ID = recurso.getIdentifier("sillaazul", "drawable", packageName)
                sillas8.setImageResource(ID)
                puestos_disponibles -= 1
                puestos.text = puestos_disponibles.toString()
            }
        }
        sillas9.setOnClickListener {
            counter += 1
            if (counter <= total) {
                val recurso: Resources = resources
                val ID = recurso.getIdentifier("sillaazul", "drawable", packageName)
                sillas9.setImageResource(ID)
                puestos_disponibles -= 1
                puestos.text = puestos_disponibles.toString()
            }
        }
        sillas10.setOnClickListener {
            counter += 1
            if (counter <= total) {
                val recurso: Resources = resources
                val ID = recurso.getIdentifier("sillaazul", "drawable", packageName)
                sillas10.setImageResource(ID)
                puestos_disponibles -= 1
                puestos.text = puestos_disponibles.toString()
            }
        }
        sillas11.setOnClickListener {
            counter += 1
            if (counter <= total) {
                val recurso: Resources = resources
                val ID = recurso.getIdentifier("sillaazul", "drawable", packageName)
                sillas11.setImageResource(ID)
                puestos_disponibles -= 1
                puestos.text = puestos_disponibles.toString()
            }
        }
        sillas12.setOnClickListener {
            counter += 1
            if (counter <= total) {
                val recurso: Resources = resources
                val ID = recurso.getIdentifier("sillaazul", "drawable", packageName)
                sillas12.setImageResource(ID)
                puestos_disponibles -= 1
                puestos.text = puestos_disponibles.toString()
            }
        }
        sillas13.setOnClickListener {
            counter += 1
            if (counter <= total) {
                val recurso: Resources = resources
                val ID = recurso.getIdentifier("sillaazul", "drawable", packageName)
                sillas13.setImageResource(ID)
                puestos_disponibles -= 1
                puestos.text = puestos_disponibles.toString()
            }
        }
        sillas14.setOnClickListener {
            counter += 1
            if (counter <= total) {
                val recurso: Resources = resources
                val ID = recurso.getIdentifier("sillaazul", "drawable", packageName)
                sillas14.setImageResource(ID)
                puestos_disponibles -= 1
                puestos.text = puestos_disponibles.toString()
            }
        }
        sillas15.setOnClickListener {
            counter += 1
            if (counter <= total) {
                val recurso: Resources = resources
                val ID = recurso.getIdentifier("sillaazul", "drawable", packageName)
                sillas15.setImageResource(ID)
                puestos_disponibles -= 1
                puestos.text = puestos_disponibles.toString()
            }
        }
        sillas16.setOnClickListener {
            counter += 1
            if (counter <= total) {
                val recurso: Resources = resources
                val ID = recurso.getIdentifier("sillaazul", "drawable", packageName)
                sillas16.setImageResource(ID)
                puestos_disponibles -= 1
                puestos.text = puestos_disponibles.toString()
            }
        }
        sillas17.setOnClickListener {
            counter += 1
            if (counter <= total) {
                val recurso: Resources = resources
                val ID = recurso.getIdentifier("sillaazul", "drawable", packageName)
                sillas17.setImageResource(ID)
                puestos_disponibles -= 1
                puestos.text = puestos_disponibles.toString()
            }
        }
        sillas18.setOnClickListener {
            counter += 1
            if (counter <= total) {
                val recurso: Resources = resources
                val ID = recurso.getIdentifier("sillaazul", "drawable", packageName)
                sillas18.setImageResource(ID)
                puestos_disponibles -= 1
                puestos.text = puestos_disponibles.toString()
            }
        }


//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        btncomprar.setOnClickListener {
            startActivity(
                Intent(this, Factura::class.java)
                    .putExtra("Menores", menores)
                    .putExtra("Adultos", adultos)
                    .putExtra("Monto", monto)
                    .putExtra("Usuario", usuario)
            )
        }

    }

}