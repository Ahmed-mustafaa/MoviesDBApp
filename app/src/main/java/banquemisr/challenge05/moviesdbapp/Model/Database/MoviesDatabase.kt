package banquemisr.challenge05.moviesdbapp.Model.Database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import banquemisr.challenge05.moviesdbapp.Model.Database.Movie
@Database(entities = [Movie::class], version =3, exportSchema = false)
@TypeConverters(Convertor::class)
abstract class MoviesDatabase: RoomDatabase() {
    abstract fun movieDao(): MovieDao
}
