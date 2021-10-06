import com.example.app.API.Usuarios
import com.example.app.API.Clientes
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST
/////////////////////////// API //////////////////////////
// Interface encargada de administrar las funciones y las rutas de los diferentes metodos HTTP
interface RestAPI {
        @Headers("Content-Type: application/json")
        @POST("/clientes")
        fun addUser(@Body userData: Usuarios): Call<Usuarios>

        @GET("/api/cliente")
        fun getAccount(): Call<List<Clientes>>
}