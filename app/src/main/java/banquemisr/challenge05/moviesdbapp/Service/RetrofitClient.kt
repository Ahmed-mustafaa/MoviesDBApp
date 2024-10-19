package banquemisr.challenge05.moviesdbapp.Service

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class RetrofitClient {
    companion object {

        var API_KEY: String = "33b3acf97a8db8641ca87066b3947177"
        private val BASE_URL = "https://api.themoviedb.org/3/"
        private val okHttpClient = OkHttpClient.Builder()
            .connectTimeout(60, TimeUnit.SECONDS)  // Connection timeout
            .readTimeout(60, TimeUnit.SECONDS)     // Read timeout
            .writeTimeout(30, TimeUnit.SECONDS)    // Write timeout
            .build()

        private val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()



        val apiService: TmdbApiService by lazy {
            retrofit.create(TmdbApiService::class.java)
        }

    }
}