package banquemisr.challenge05.moviesdbapp.ViewModel

import android.util.Log
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import banquemisr.challenge05.moviesdbapp.Model.Database.Movie

import banquemisr.challenge05.moviesdbapp.Model.MovieRepository.FakeMovieRepository
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule

import org.junit.Test

class MoviesViewModelTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var viewModel: MoviesViewModel
    private lateinit var fakeRepository: FakeMovieRepository
    private val testDispatcher = UnconfinedTestDispatcher()
    val movies = listOf( Movie(
    false,
    "movie1 path",
    1, 120,
    "EN",
    "OMovie1 Title",
    "Original1 Title", 0.0,
    0.0.toString(), "Poster1/test",
    12000000, 0, "movie1 Title",
    false,
    00.0, 1
    ),
    Movie(
    false,
    "movie2 path",
    2, 120,
    "EN",
    "OTitle",
    "Original Title", 0.0,
    0.0.toString(), "Poster/test",
    12000000, 0, "Test Title",
    false,
    00.0, 1
    )
   )
    @Before
    fun setup() {

        Dispatchers.setMain(testDispatcher)
        fakeRepository = FakeMovieRepository()
        fakeRepository.clearMovies()

        viewModel = MoviesViewModel(fakeRepository)
    }
    @After
    fun tearDown() {
        Dispatchers.resetMain() // Reset the main dispatcher after the test
    }

    @Test
    fun getNowPlayingMovies(): Unit = runBlocking {
        // Setup initial state
        val nowPlayingMovie = mutableListOf(
            Movie(
                false,
                "movie1 path",
                1, 120,
                "EN",
                "OMovie1 Title",
                "Original1 Title", 0.0,
                0.0.toString(), "Poster1/test",
                12000000, 0, "movie1 Title",
                false,
                00.0, 1
            ),
            Movie(
                false,
                "movie2 path",
                2, 120,
                "EN",
                "OTitle",
                "Original Title", 0.0,
                0.0.toString(), "Poster/test",
                12000000, 0, "Test Title",
                false,
                00.0, 1
            )
        )

        fakeRepository.insertMovies(nowPlayingMovie)

        // Call the method to test
        fakeRepository.insertMovies(nowPlayingMovie)

        // Call the method to test
        viewModel.fetchNowPlayingMovies()

        // Assert the expected outcome
        assertThat(viewModel.nowPlayingMovies.value).containsExactlyElementsIn(nowPlayingMovie)
    }
    @Test
     fun getPopularMovies(): Unit = runBlocking {
        val popularMovies = mutableListOf(
            Movie(
                false,
                "movie1 path",
                1, 120,
                "EN",
                "OMovie1 Title",
                "Original1 Title", 0.0,
                0.0.toString(), "Poster1/test",
                12000000, 0, "movie1 Title",
                false,
                00.0, 1
            ),
            Movie(
                false,
                "movie2 path",
                2, 120,
                "EN",
                "OTitle",
                "Original Title", 0.0,
                0.0.toString(), "Poster/test",
                12000000, 0, "Test Title",
                false,
                00.0, 1
            )
        )

        fakeRepository.insertMovies(popularMovies)

        // Call the method to test
        fakeRepository.insertMovies(popularMovies)

        // Call the method to test
        viewModel.fetchNowPlayingMovies()

        // Assert the expected outcome
        assertThat(viewModel.nowPlayingMovies.value).containsExactlyElementsIn(popularMovies)
    }

    @Test
    fun getCachedMovies() : Unit = runBlocking {

        val cachedMovies = movies

        fakeRepository.insertMovies(cachedMovies)
        println("Inserted cached movies: $cachedMovies")

        // Collect the emitted movies from the flow in the ViewModel
        val actualMovies = fakeRepository.getCachedMovies().first()
        println("Actual movies from repository: $actualMovies")

        // Assert that the cached movies match the expected values
            assertThat(actualMovies).containsExactlyElementsIn(cachedMovies)

    }
    @Test
    fun getMovieDetailsByID() = runBlocking {
           // Given a movie
        val movie = Movie(
            false,
            "movie1 path",
            1,
            120,
            "EN",
            "OMovie1 Title",
            "Original1 Title",
            0.0,
            "0.0",
            "Poster1/test",
            12000000,
            0,
            "movie1 Title",
            false,
            0.0,
            1
        )

        fakeRepository.insertMovie(movie)

        // When fetching the movie by ID
        viewModel.getMovieById(1)

        // Observe the movie details LiveData or StateFlow
        val fetchedMovie = fakeRepository.getMovieDetailsByID(movie.id," ").first()// Assuming movieDetails is your MutableLiveData or StateFlow

        // Assert that the fetched movie matches the expected movie
        assertThat(fetchedMovie).isEqualTo(movie)
    }


}