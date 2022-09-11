package me.emakeeva.teta_course02_01.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import me.emakeeva.teta_course02_01.data.entity.News

@Dao
interface NewsDao {
    @Query("SELECT * FROM news")
    fun getNews(): List<News>

    @Insert
    fun insertAll(data: List<News>)
}