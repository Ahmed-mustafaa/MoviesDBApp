package banquemisr.challenge05.moviesdbapp.Model.MovieRepository

import android.net.http.HttpException
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresExtension
import banquemisr.challenge05.moviesdbapp.Model.Database.MovieDao
import banquemisr.challenge05.moviesdbapp.Model.Database.MovieEntity
import banquemisr.challenge05.moviesdbapp.Model.Movie
import banquemisr.challenge05.moviesdbapp.Service.TmdbApiService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.withContext

class MovieRepository (private  val apiService: TmdbApiService,
                       private val movieDao: MovieDao
) {
    @RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
    suspend fun getNowPlayingMovies(apikey: String): Flow<List<Movie>> = flow {
        try{
            val response = apiService.getNowPlayingMovies(apikey)
            val movies = response.results.map {
                Movie(
                    it.adult,
                    it.backdrop_path,
                    it.id,
                    it.original_language,
                    it.original_title,
                    it.overview,
                    it.popularity,
                    it.poster_path,
                    it.release_date,
                    it.title,
                    it.video,
                    it.vote_average,
                    it.vote_count

                )
            }
            movieDao.insertMovies(movies)
            emit(movies)

        }catch (e: HttpException){
            val cachedMovies = movieDao.getAllMovies().first()  // Collect the Flow into a List
            emit(cachedMovies)        }


        }.flowOn(Dispatchers.IO)

    suspend fun insertMovies(movies: List<Movie>){
        movieDao.insertMovies(movies)
    }
    suspend fun insertMovie(movie: Movie){
        movieDao.insertMovie(movie)
    }

    @RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
    suspend fun getPopularMovies(apikey: String): Flow<List<Movie>> = flow {
        try {
            val response = apiService.getPopularMovies(apikey)
            Log.d("PopularMoviesFromMovieRepository", "Response: $response")
            val movies = response.results.map {
                Movie(
                    it.adult,
                    it.backdrop_path,
                    it.id,
                    it.original_language,
                    it.original_title,
                    it.overview,
                    it.popularity,
                    it.poster_path,
                    it.release_date,
                    it.title,
                    it.video,
                    it.vote_average,
                    it.vote_count


                )
            }

                movieDao.insertMovies(movies)
                emit(movies)


        }catch (e: HttpException){
            val cachedMovies = movieDao.getAllMovies().first()
            emit(cachedMovies)
        }
    }.flowOn(Dispatchers.IO)

}