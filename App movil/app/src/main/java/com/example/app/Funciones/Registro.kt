package com.example.app.Funciones

import RestAPIService
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.example.app.R
import com.example.app.API.Data.Usuarios
import kotlinx.android.synthetic.main.registrarse.*

class Registro: AppCompatActivity() {

    //Se crea el array para almacenar los usuarios creados en la actividad de Registro
    var usuarios_registrados = ArrayList<String>()

    //Se crea el array para almacenar las contraseñas guardadas en la actividad de Registro
    var contrasenas_registradas = ArrayList<String>()

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.registrarse)

        //Variables para recibir los datos de entrada de los usuarios
        val usuario_input2 = findViewById<EditText>(R.id.inputcorreor) as EditText
        val contrasena_input2 =  findViewById<EditText>(R.id.inputcontrasenar) as EditText
        val name_input = findViewById<EditText>(R.id.inputnombre) as EditText
        val Fname_input = findViewById<EditText>(R.id.inputpapellido) as EditText
        val Lname_input = findViewById<EditText>(R.id.inputsapellido) as EditText
        val id_input = findViewById<EditText>(R.id.inputcedula) as EditText
        val phone_input = findViewById<EditText>(R.id.inputtelefono) as EditText

        //Sección de función para el botón de registrar
        btnregistrar.setOnClickListener {

            //Las variables toman los datos de entrada en la ventana de registro
            //Las que se deben almacenar en el array son usario y contraseña
            val usuario = usuario_input2.text.toString()
            val contrasena = contrasena_input2.text.toString()
            val nombre = name_input.text.toString()
            val p_apellido = Fname_input.text.toString()
            val s_apellido = Lname_input.text.toString()
            val cedula = id_input.text.toString()
            val telefono = phone_input.text.toString()

            //Se almacenan los datos para que estos puedan ser enviados a la actividad principal
            if (usuarios_registrados.contains(usuario)){
                //Se despliega un mensaje de alerta solicitando datos válidos para el ingreso
                Toast.makeText(this, "Este usuario ya ha sido registrado", Toast.LENGTH_LONG).show()
            }
            else{
                usuarios_registrados.add(usuario)
                contrasenas_registradas.add(contrasena)
                Log.d("Usuarios: ", usuarios_registrados.toString())

                ////////////// API //////////////////
                val API = RestAPIService()
                val userInfo = Usuarios(
                    userID = null, userDate = "", userEmail = usuario, userFName = p_apellido,
                    userUID = cedula.toInt(),
                    userSName = s_apellido,
                    userName = nombre,
                    userPassword = contrasena,
                    userPhone = telefono.toInt()
                )

                //Funcion encargada de hacer el POST del nuevo usuario registrado
                API.addUser(userInfo)
            }
        }

        btnsalir.setOnClickListener {
            startActivity(Intent(this, Login::class.java).
            putStringArrayListExtra("Usuarios",usuarios_registrados).
            putStringArrayListExtra("Contrasena",contrasenas_registradas))
        }

    }

}