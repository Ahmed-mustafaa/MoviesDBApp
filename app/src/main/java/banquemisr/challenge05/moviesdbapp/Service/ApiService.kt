package banquemisr.challenge05.moviesdbapp.Service

import banquemisr.challenge05.moviesdbapp.Model.Database.MovieEntity
import banquemisr.challenge05.moviesdbapp.Model.NowPlayingResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @GET("movie/now_playing")
    suspend fun getNowPlayingMovies(
        @Query("api_key") apiKey: String,

    ): NowPlayingResponse

    @GET("movie/upcoming")
    suspend fun getUpcomingMovies(
        @Query("api_key") apiKey: String,
    ): NowPlayingResponse

    @GET("movie/popular")
    suspend fun getPopularMovies(
        @Query("api_key") apiKey: String,
    ): NowPlayingResponse
    @GET("movie/{id}")
        suspend fun getMovieDetails(@Path("id") movieId: Int?, @Query("api_key") apiKey: String): MovieEntity
    }
