
package banquemisr.challenge05.moviesdbapp.Model.MovieRepository

import banquemisr.challenge05.moviesdbapp.Model.Database.Movie
import android.net.http.HttpException
import android.os.Build
import androidx.annotation.RequiresExtension

import banquemisr.challenge05.moviesdbapp.Model.Database.MovieDao
import banquemisr.challenge05.moviesdbapp.Model.Database.MovieEntity

import banquemisr.challenge05.moviesdbapp.Service.ApiService
import banquemisr.challenge05.moviesdbapp.Service.Responses
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.withContext


class MovieRepository (private  val apiService: ApiService,
                       private val movieDao: MovieDao
) : IMovieRepository {
    @RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
    override suspend fun getNowPlayingMovies(apikey: String): Flow<List<Movie>> = flow {
        try {
            val response = apiService.getNowPlayingMovies(apikey)
            val movies: List<Movie> = response.results
            withContext(Dispatchers.IO) {
                movieDao.insertMovies(movies)
            }
            emit(movies)

        } catch (e: HttpException) {
            Responses.loading("Error fetching now playing movies , Loading cashed movies instead")
            val cachedMovies = movieDao.getAllMovies().first()  // Collect the Flow into a List
            emit(cachedMovies)
        }
    }.flowOn(Dispatchers.IO)


    override suspend fun insertMovies(movies: List<Movie>) {
        withContext(Dispatchers.IO) {
            movieDao.insertMovies(movies)
        }
    }


    override suspend fun getCachedMovies(): Flow<List<Movie>> {
        return movieDao.getAllMovies()
    }

    override suspend fun insertMovieEntity(movie: Movie) {
        TODO("Not yet implemented")
    }

    /*  override suspend fun insertMovieById(movie: MovieEntity) {
          TODO("Not yet implemented")
      }*/

    override suspend fun deleteMovie(movie: Movie) {
        withContext(Dispatchers.IO) {
            movieDao.deleteMovie(movie)
        }    }

    override suspend fun insertMovie(movie: Movie) {
        withContext(Dispatchers.IO) {
            movieDao.insertMovie(movie)
        }    }


    @RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
    override suspend fun getPopularMovies(apikey: String): Flow<List<Movie>> = flow {
        try {
            val response = apiService.getPopularMovies(apikey)

            val movies: List<Movie> = response.results
            withContext(Dispatchers.IO) {
                movieDao.insertMovies(movies)
            }

            emit(movies)

        } catch (e: Exception) {
            Responses.error("Error fetching popular movies ", null)
            val cachedMovies = movieDao.getAllMovies().first()
            emit(cachedMovies)
        }
    }.flowOn(Dispatchers.IO)

    @RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
    override suspend fun getUpComingMovies(apikey: String): Flow<List<Movie>> = flow {
        try {
            val response = apiService.getUpcomingMovies(apikey)
            val movies: List<Movie> = response.results
            withContext(Dispatchers.IO) {
                movieDao.insertMovies(movies)
            }
            emit(movies)


        } catch (e: HttpException) {
            val cachedMovies = movieDao.getAllMovies().first()
            emit(cachedMovies)
        }
    }.flowOn(Dispatchers.IO)


    @RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
    override suspend fun getMovieDetailsByID(movieID: Int?, apikey: String): Flow<MovieEntity?> = flow {
        if (movieID == null) {
            emit(null) // Emit null if movieID is null
            return@flow
        }

        try {
            val response = apiService.getMovieDetails(movieID, apikey)
            emit(response) // Emit the fetched MovieEntity
        } catch (e: Exception) {
            // Log the error or handle it accordingly
            Responses.error("Error fetching movie details using its ID: ${e.message}", null)

            // Optionally, you could fetch from the cache if needed:
            val cachedMovie = movieDao.getMovieById(movieID).firstOrNull() // Ensure you have this method in your DAO
            emit(cachedMovie) // Emit cached data if available
        }
    }.flowOn(Dispatchers.IO)

}

