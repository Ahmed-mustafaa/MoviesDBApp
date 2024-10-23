package banquemisr.challenge05.moviesdbapp.Model.MovieRepository

import banquemisr.challenge05.moviesdbapp.Model.Database.MovieDao
import banquemisr.challenge05.moviesdbapp.ViewModel.MoviesViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before


import org.junit.Test

class MovieRepositoryTest {
    private lateinit var viewModel: MoviesViewModel
    private lateinit var movieRepository: MovieRepository
    private lateinit var movieDao: MovieDao
    private lateinit var apiService: ApiServiceTestDouble
    private val testDispatcher = UnconfinedTestDispatcher()

    @Before
     fun setup(){
        Dispatchers.setMain(testDispatcher)
        apiService= ApiServiceTestDouble()
/*
        movieDao= banquemisr.challenge05.moviesdbapp.Model.Database.MovieDao
*/
        movieRepository= MovieRepository(apiService,movieDao)
        viewModel = MoviesViewModel(movieRepository)


    }
    @After
    fun tearDown() {
        Dispatchers.resetMain() // Reset the main dispatcher after the test
    }

    @Test
    fun `testing getNowPlayingMovies function`(): Unit = runTest{
        viewModel.fetchNowPlayingMovies()
        val result = viewModel.nowPlayingMovies.value
        assert(result.get(0).title=="Test Title")


       /* viewModel.nowPlayingMovies.collect{
            assertEquals(it.size,1)
            assertEquals(it[0].title,"Test Title")
        }*/
    }

    @Test
    fun insertMovies() {
        viewModel
    }

    @Test
    fun insertMovie() {
    }

    @Test
    fun getPopularMovies() {
    }

    @Test
    fun getUpComingMovies() {
    }

    @Test
    fun getMovieDetailsByID() {
    }
}