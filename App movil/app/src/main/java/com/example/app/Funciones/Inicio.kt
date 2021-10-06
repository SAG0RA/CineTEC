package com.example.app.Funciones

import RestAPIService
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import com.example.app.Database.Cliente.ClientesDB
import com.example.app.R

class Inicio: AppCompatActivity() {

    lateinit var  handler: Handler
    var db:ClientesDB? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.inicio)
        ///////////////////
        val API = RestAPIService()

        db = ClientesDB(this)
        API.getClient()

        handler = Handler()
        handler.postDelayed({

            val intent = Intent(this , Login::class.java)
            startActivity(intent)
            finish()
        }, 3000)
    }

}