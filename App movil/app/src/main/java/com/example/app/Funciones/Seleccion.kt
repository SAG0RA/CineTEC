package com.example.app.Funciones

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.app.R
import kotlinx.android.synthetic.main.seleccion.*

class Seleccion : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.seleccion)

        btnsiguiente.setOnClickListener {
                startActivity(Intent(this, Asientos::class.java))
            }
    }

}