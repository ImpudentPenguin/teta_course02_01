package me.emakeeva.teta_course02_01.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import me.emakeeva.teta_course02_01.data.entity.News

@Database(entities = [News::class], version = 1)
abstract class NewsDatabase : RoomDatabase() {
    abstract fun userDao(): NewsDao
}