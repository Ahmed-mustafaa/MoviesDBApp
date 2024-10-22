package banquemisr.challenge05.moviesdbapp.Model.Database

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SmallTest
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
@ExperimentalCoroutinesApi
@RunWith(AndroidJUnit4::class)
@SmallTest
class MovieDaoTest{
    @get:Rule
    var instantTaskExecutorRunWith= InstantTaskExecutorRule()
    // creating a database in memory
    private lateinit var Database:MoviesDatabase
    private lateinit var Dao:MovieDao
    @Before
    fun setup() {
        Database = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            MoviesDatabase::class.java).allowMainThreadQueries().build()
        Dao = Database.movieDao()
    }
    @After
    fun teardown() {
        Database.close()
    }

    // creating a list to store movies ( as a database)


@Test
 fun insertMovie()= runTest {
  val movie1 =  Movie(false,
        "movie1 path",
        1,120,
        "EN",
        "OMovie1 Title",
        "Original1 Title",0.0,
        0.0.toString(),"Poster1/test",
        12000000,0,"movie1 Title",
        false,
        00.0,1
    )
    val movie2 =  Movie(false,
        "movie2 path",
        2,120,
        "EN",
        "OTitle",
        "Original Title",0.0,
        0.0.toString(),"Poster/test",
        12000000,0,"Test Title",
        false,
        00.0,1
    )
    Dao.insertMovie(movie1)
    Dao.insertMovie(movie2)
    val allMovies = Dao.getAllMovies().first()
        assertThat(allMovies).containsExactly(movie1,movie2)

}



fun insertMovies()= runTest {
    // creating a list to store movies
    val movies = mutableListOf<Movie>()
    val movie1 =  Movie(false,
        "movie1 path",
        1,120,
        "EN",
        "OMovie1 Title",
        "Original1 Title",0.0,
        0.0.toString(),"Poster1/test",
        12000000,0,"movie1 Title",
        false,
        00.0,1
    )
    val movie2 =  Movie(false,
        "movie2 path",
        2,120,
        "EN",
        "OTitle",
        "Original Title",0.0,
        0.0.toString(),"Poster/test",
        12000000,0,"Test Title",
        false,
        00.0,1
    )
    movies.add(movie1)
    movies.add(movie2)
    Dao.insertMovies(movies)
    val allMovies = Dao.getAllMovies().first()
    assertThat(allMovies).containsExactly(movie1,movie2)

}

}