
package banquemisr.challenge05.moviesdbapp.Model.MovieRepository

import banquemisr.challenge05.moviesdbapp.Model.Database.Movie
import android.net.http.HttpException
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresExtension

import banquemisr.challenge05.moviesdbapp.Model.Database.MovieDao
import banquemisr.challenge05.moviesdbapp.Model.Database.MovieEntity

import banquemisr.challenge05.moviesdbapp.Service.ApiService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn


class MovieRepository (private  val apiService: ApiService,
                       private val movieDao: MovieDao
) {
    @RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
    suspend fun getNowPlayingMovies(apikey: String): Flow<List<Movie>> = flow {
        try {
            val response = apiService.getNowPlayingMovies(apikey)
            val movies = response.results.map {
                Movie(
                    adult = it.adult,
                    backdrop_path = it.backdrop_path,
                    id = it.id,
                    budget = it.budget,

                    original_language = it.original_language,
                    original_title = it.original_title,
                    overview = it.overview,
                    popularity = it.popularity,
                    poster_path = it.poster_path,

                    release_date = it.release_date,
                    revenue = it.revenue,
                    runtime = it.runtime,

                    title = it.title,
                    video = it.video,
                    vote_average = it.vote_average,
                    vote_count = it.vote_count
                )
            }


            movieDao.insertMovies(movies)
            emit(movies)

        } catch (e: HttpException) {
            val cachedMovies = movieDao.getAllMovies().first()  // Collect the Flow into a List
            emit(cachedMovies)
        }


    }.flowOn(Dispatchers.IO)


    suspend fun insertMovies(movies: List<Movie>) {
        movieDao.insertMovies(movies)
    }

    suspend fun insertMovie(movie: Movie) {
        movieDao.insertMovie(movie)
    }

    @RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
    suspend fun getPopularMovies(apikey: String): Flow<List<Movie>> = flow {
        try {
            val response = apiService.getPopularMovies(apikey)
            Log.d("PopularMoviesFromMovieRepository", "Response: $response")
            val movies = response.results.map {
                Movie(
                    adult = it.adult,
                    backdrop_path = it.backdrop_path,
                    id = it.id,
                    budget = it.budget,
                     // Convert origin countries to string
                    original_language = it.original_language,
                    original_title = it.original_title,
                    overview = it.overview,
                    popularity = it.popularity,
                    poster_path = it.poster_path,
                    release_date = it.release_date,
                    revenue = it.revenue,
                    runtime = it.runtime,

                    title = it.title,
                    video = it.video,
                    vote_average = it.vote_average,
                    vote_count = it.vote_count
                )
            }

            movieDao.insertMovies(movies)
            emit(movies)


        } catch (e: HttpException) {
            val cachedMovies = movieDao.getAllMovies().first()
            emit(cachedMovies)
        }
    }.flowOn(Dispatchers.IO)

    @RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
    suspend fun getUpComingMovies(apikey: String): Flow<List<Movie>> = flow {
        try {
            val response = apiService.getUpcomingMovies(apikey)
            Log.d("PopularMoviesFromMovieRepository", "Response: $response")
            val movies = response.results.map {
                Movie(
                    adult = it.adult,
                    backdrop_path = it.backdrop_path,
                    id = it.id,
                    budget = it.budget,
                    // Convert origin countries to string
                    original_language = it.original_language,
                    original_title = it.original_title,
                    overview = it.overview,
                    popularity = it.popularity,
                    poster_path = it.poster_path,
                    release_date = it.release_date,
                    revenue = it.revenue,
                    runtime = it.runtime,
                    title = it.title,
                    video = it.video,
                    vote_average = it.vote_average,
                    vote_count = it.vote_count
                )
            }



            movieDao.insertMovies(movies)
            emit(movies)


        } catch (e: HttpException) {
            val cachedMovies = movieDao.getAllMovies().first()
            emit(cachedMovies)
        }
    }.flowOn(Dispatchers.IO)


@RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
suspend fun getMovieDetailsByID(movieID: Int?, apikey: String): Flow<MovieEntity> = flow {
    try{
    val response = apiService.getMovieDetails(movieID, apikey)
    emit(response)
}

    catch (e: HttpException) {
        // Handle exceptions or cache the details if necessary
    }
}.flowOn(Dispatchers.IO)
}

