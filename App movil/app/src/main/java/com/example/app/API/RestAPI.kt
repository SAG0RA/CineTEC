import com.example.app.API.Data.Cines
import com.example.app.API.Data.Usuarios
import com.example.app.API.Data.Clientes
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
        fun getClient(): Call<List<Clientes>>

        @GET("/api/sucursal")
        fun getCine(): Call<List<Cines>>
}