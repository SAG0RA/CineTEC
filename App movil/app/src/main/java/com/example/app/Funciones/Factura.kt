package com.example.app.Funciones

import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.app.Database.Cliente.ClientesDB
import com.example.app.R
import kotlinx.android.synthetic.main.factura.*

class Factura : AppCompatActivity() {

    private var db: ClientesDB? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.factura)

        val usuario = intent.getStringExtra("Usuario")
        val monto = intent.getIntExtra("Monto", 0)
        val menores = intent.getIntExtra("Menores",0)
        val adultos = intent.getIntExtra("Adultos",0)
        ///////////////// BASE DE DATOS ///////////////
        db = ClientesDB(this)
        val facturacion = db!!.getDatos()
        ///////////////////////////////////////////////

        val i = facturacion.indexOfFirst { it.usuario == usuario }
        val nombre = facturacion[i].nombre
        val cedula = facturacion[i].cedula
        val apellido = facturacion[i].apellido

        Nombre.text = nombre + " " + apellido
        Cedula.text = cedula.toString()
        Monto.text = "₡" + monto.toString()
        comprobante.text = ((0..1219392492).random()).toString()
        descripcion.text = "Cantidad de personas: " + "\n Menores: " + menores.toString() + "\n Adultos: " + adultos.toString()
    }
}