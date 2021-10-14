import android.util.Log
import com.example.app.API.Data.Cines
import com.example.app.API.Data.Clientes
import com.example.app.API.Data.Peliculas
import com.example.app.API.Data.Usuarios
import com.example.app.Database.Cine.CinesDB
import com.example.app.Database.Cliente.ClientesDB
import com.example.app.Database.Pelicula.PeliculasDB
import com.example.app.Funciones.Inicio
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

    /**Funcion encargada de enviar la solicitud GET al REST API en /api/cliente
     * @param db: Base de datos donde se guardara la correspondiente info
     */
    fun getClient(db: ClientesDB) {
        val retrofit = ServiceBuilder.buildService(RestAPI::class.java)
        retrofit.getClient().enqueue(object : Callback<List<Clientes>> {
            override fun onResponse(call: Call<List<Clientes>>, response: Response<List<Clientes>>) {
                val datos = response.body()

                if (datos != null) {
                    Inicio().sync_Cliente(datos,db)
                }

                for (c in datos!!)
// Print para verificar que se haya hecho bien la solicitud
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

            override fun onFailure(call: Call<List<Clientes>>, t: Throwable) {
                Log.d("Error", t.message)
            }
        })

    }

    /**Funcion encargada de enviar la solicitud GET al REST API en /api/sucursal
     * @param db: Base de datos donde se guardara la correspondiente info
     */
    fun getCine(db: CinesDB){
        val retrofit = ServiceBuilder.buildService(RestAPI::class.java)
        retrofit.getCine().enqueue(object : Callback<List<Cines>> {
            override fun onResponse(call: Call<List<Cines>>, response: Response<List<Cines>>) {
                val datos = response.body()

                if (datos != null) {
                    Inicio().sync_Cines(datos,db)
                }

                for (c in datos!!)
// Print para verificar que se haya hecho bien la solicitud
                    Log.d(
                        "CINE: ",
                             "Cine: ${c.nombrecine} " +
                                "\n Ubicacion: ${c.ubicacion} " +
                                "\n Cantidad de salas: ${c.cantidadsalas}"
                    )
            }
            override fun onFailure(call: Call<List<Cines>>, t: Throwable) {
                Log.d("Error", t.message)
            }
        })
    }

    fun getPelicula(db: PeliculasDB){
        val retrofit = ServiceBuilder.buildService(RestAPI::class.java)
        retrofit.getPelicula().enqueue(object : Callback<List<Peliculas>> {
            override fun onResponse(call: Call<List<Peliculas>>, response: Response<List<Peliculas>>) {
                val datos = response.body()

                if (datos != null) {
                    Inicio().sync_Peliculas(datos,db)
                }

                for (c in datos!!)
// Print para verificar que se haya hecho bien la solicitud
                    Log.d(
                        "PELICULA: ",
                           "\n Nombre original: ${c.nombreog} " +
                                "\n Imagen: ${c.imagen} " +
                                "\n Proyecciones: ${c.proyecciones}"
                    )
            }
            override fun onFailure(call: Call<List<Peliculas>>, t: Throwable) {
                Log.d("Error", t.message)
            }
        })
    }

}