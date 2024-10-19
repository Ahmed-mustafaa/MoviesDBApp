package banquemisr.challenge05.moviesdbapp.Model.Database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import banquemisr.challenge05.moviesdbapp.Model.Movie

@Database(entities = [Movie::class], version = 2, exportSchema = false)
abstract class MoviesDatabase: RoomDatabase() {
    abstract fun movieDao(): MovieDao
}
