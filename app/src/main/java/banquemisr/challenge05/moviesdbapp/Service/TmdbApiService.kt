package banquemisr.challenge05.moviesdbapp.Service

import banquemisr.challenge05.moviesdbapp.Model.NowPlayingResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface TmdbApiService {
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
}