import android.util.Log
import com.example.app.API.Cliente
import com.example.app.API.Usuarios
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RestAPIService {
    /**Funcion encargada de enviar la solicitud POST al REST API a /clientes
     * @param userData: Datos que se van a enviar
     */
    fun addUser(userData: Usuarios) {
        val retrofit = ServiceBuilder.buildService(RestAPI::class.java)
        retrofit.addUser(userData).enqueue(
            object : Callback<Usuarios> {
                override fun onFailure(call: Call<Usuarios>, t: Throwable) {
                    Log.d("Error", t.message)
                }

                override fun onResponse(call: Call<Usuarios>, response: Response<Usuarios>) {
                    Log.d("Exito", "JSON enviado")
                }
            }
        )
    }

    /**Funcion encargada de enviar la solicitud GET al REST API en /cuentas
     * @param detalles: la pantalla donde se mostraran los datos de la cuenta
     */
    fun getClient() {
        val retrofit = ServiceBuilder.buildService(RestAPI::class.java)
        retrofit.getAccount().enqueue(object : Callback<List<Cliente>> {
            override fun onResponse(call: Call<List<Cliente>>, response: Response<List<Cliente>>) {
                val datos = response.body()

// Aqui se envian los datos correspondientes
//                if (datos != null) {
//                    Seleccion().verCuenta(datos,detalles)
//                }

// Print para verificar que se haya hecho bien la solicitud
                for (c in datos!!)
                    Log.d(
                        "CLIENTE: ",
                        "Cedula: ${c.cedula} " +
                                "\n Nombre: ${c.pnombre} " +
                                "\n Apellido: ${c.apellido} " +
                                "\n Telefono: ${c.telefono} " +
                                "\n Fecha de nacimiento: ${c.fechanac}" +
                                "\n Edad: ${c.edad}" +
                                "\n Usuario: ${c.usuario}" +
                                "\n Contrasena: ${c.contraseña}"
                    )

            }

            override fun onFailure(call: Call<List<Cliente>>, t: Throwable) {
                Log.d("Error", t.message)
            }
        })

    }

}