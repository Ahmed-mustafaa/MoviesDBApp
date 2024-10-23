package banquemisr.challenge05.moviesdbapp.Model.Database

import banquemisr.challenge05.moviesdbapp.Model.Genre

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
class Convertor {
        @TypeConverter
        fun fromGenreList(genres: List<Genre>): String {
            return Gson().toJson(genres)
        }

        @TypeConverter
        fun toGenreList(genresString: String): List<Genre> {
            val listType = object : TypeToken<List<Genre>>() {}.type
            return Gson().fromJson(genresString, listType)
        }
    }
