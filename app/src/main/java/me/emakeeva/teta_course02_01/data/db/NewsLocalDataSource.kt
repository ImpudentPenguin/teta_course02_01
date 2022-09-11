package me.emakeeva.teta_course02_01.data.db

import android.content.Context
import androidx.room.Room
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import me.emakeeva.teta_course02_01.data.entity.*

class NewsLocalDataSource(private val context: Context) {

    private val database by lazy {
        Room.databaseBuilder(
            context,
            NewsDatabase::class.java,
            "database"
        ).build()
    }

    suspend fun getNews(): Result<List<News>, Throwable> {
        return runOperationCatching {
            withContext(Dispatchers.IO) {
                database.userDao().getNews()
            }
        }
    }

    suspend fun insertNews(data: List<News>): VoidResult<Throwable> {
        return runOperationCatching {
            withContext(Dispatchers.IO) {
                database.userDao().insertAll(data)
            }
        }
    }
}