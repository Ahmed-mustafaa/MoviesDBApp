package banquemisr.challenge05.moviesdbapp.MovieRepository


import banquemisr.challenge05.moviesdbapp.Model.Movie
import banquemisr.challenge05.moviesdbapp.Model.MovieEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class FakeMovieRepository : IMovieRepository {
    private val movies = mutableSetOf<Movie>() // Use Movie directly
    private val movieDetailsByID = mutableMapOf<Int, Movie>()
    private val movieEntityDetailsByID = mutableMapOf<Int, MovieEntity>()
        private fun MovieEntity.toMovie(): Movie {
        return Movie(
            adult = this.adult,
            backdrop_path = this.backdrop_path,
            id = this.id,
            original_language = this.original_language,
            original_title = this.original_title,
            overview = this.overview,
            popularity = this.popularity,
            poster_path = this.poster_path,
            release_date = this.release_date,
            runtime = this.runtime,
            title = this.title,
            video = this.video,
            vote_average = this.vote_average,
            vote_count = this.vote_count,
            budget = this.budget,
            revenue = this.revenue
        )
    }

    override suspend fun getNowPlayingMovies(apikey: String): Flow<List<Movie>> {
        return flow { emit(movies.toList()) } // Adjust as needed
    }

    override suspend fun getPopularMovies(apiKey: String): Flow<List<Movie>> {
        return flow { emit(movies.toList()) }      }

    override suspend fun getUpComingMovies(apiKey: String): Flow<List<Movie>> {
        return flow { emit(movies.toList()) }    }

    override suspend fun insertMovies(movies: List<Movie>) {
        this.movies.addAll(movies)
    }

    override suspend fun getCachedMovies(): Flow<List<Movie>> {
        return flow {
            println("Emitting movies: $movies")
            emit(movies.toList())
        }
    }

    override suspend fun insertMovieEntity(movie: Movie) {
        TODO("Not yet implemented")
    }


    override suspend fun deleteMovie(movie: Movie) {
        movies.remove(movie)
    }

    override suspend fun getMovieDetailsByID(movieID: Int?, apikey: String): Flow<MovieEntity> {
        return flow {
            val movieDetail = movieEntityDetailsByID[movieID]
            if (movieDetail != null) {
                insertMovie(movieDetail.toMovie())
                emit(movieDetail)
            } else {
               println("Movie not found with ID: $movieID")
            }
        }.flowOn(Dispatchers.IO)
    }

    override suspend fun insertMovie(movie: Movie) {
        movies.add(movie)
    }
    fun clearMovies() {
        movies.clear()
    }

}
