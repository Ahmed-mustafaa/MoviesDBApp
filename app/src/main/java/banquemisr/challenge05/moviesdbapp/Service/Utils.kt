package banquemisr.challenge05.moviesdbapp.Service

import okhttp3.OkHttpClient
import java.util.concurrent.TimeUnit

class Utils {
    companion object{
         const val API_KEY: String = "33b3acf97a8db8641ca87066b3947177"
         const  val BASE_URL = "https://api.themoviedb.org/3/"
          val okHttpClient = OkHttpClient.Builder()
            .connectTimeout(60, TimeUnit.SECONDS)  // Connection timeout
            .readTimeout(60, TimeUnit.SECONDS)     // Read timeout
            .writeTimeout(30, TimeUnit.SECONDS)    // Write timeout
            .build()
    }
}