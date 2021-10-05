import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.security.SecureRandom
import java.security.cert.CertificateException
import java.security.cert.X509Certificate
import javax.net.ssl.HostnameVerifier
import javax.net.ssl.SSLContext
import javax.net.ssl.TrustManager
import javax.net.ssl.X509TrustManager

// Object class encargada de administrar el enlace HTTP y agregar los servicios Retrofit
object ServiceBuilder {
    private val client = OkHttpClient.Builder().build()
    private val retrofit = Retrofit.Builder()
        .baseUrl("http://192.168.18.4:80") //http://192.168.18.4:5000/
        .addConverterFactory(GsonConverterFactory.create())
        .client(client)
        .build()

//    fun unSafeOkHttpClient(): OkHttpClient.Builder {
//        try {
//            // Create a trust manager that does not validate certificate chains
//            val trustAllCerts = arrayOf<TrustManager>(object : X509TrustManager {
//                @Throws(CertificateException::class)
//                override fun checkClientTrusted(chain: Array<java.security.cert.X509Certificate>, authType: String) {
//                }
//
//                @Throws(CertificateException::class)
//                override fun checkServerTrusted(chain: Array<java.security.cert.X509Certificate>, authType: String) {
//                }
//
//                override fun getAcceptedIssuers(): Array<java.security.cert.X509Certificate> {
//                    return arrayOf()
//                }
//            })
//
//            // Install the all-trusting trust manager
//            val sslContext = SSLContext.getInstance("SSL")
//            sslContext.init(null, trustAllCerts, java.security.SecureRandom())
//            // Create an ssl socket factory with our all-trusting manager
//            val sslSocketFactory = sslContext.socketFactory
//
//            val builder = OkHttpClient.Builder()
//            builder.sslSocketFactory(sslSocketFactory, trustAllCerts[0] as X509TrustManager)
//            builder.hostnameVerifier { _, _ -> true }
//
//            return builder
//        } catch (e: Exception) {
//            throw RuntimeException(e)
//        }
//    }

    fun<T> buildService(service: Class<T>): T{
        return retrofit.create(service)
    }
}