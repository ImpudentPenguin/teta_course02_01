package me.emakeeva.teta_course02_01.data.entity

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize
import me.emakeeva.teta_course02_01.news.NewsItem

@Parcelize
@Entity(tableName = "news")
data class News(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id: Int,
    @SerializedName("title")
    @ColumnInfo(name = "title")
    val title: String?,
    @SerializedName("description")
    @ColumnInfo(name = "description")
    val description: String?,
    @SerializedName("author")
    @ColumnInfo(name = "author")
    val author: String?
): Parcelable

fun News.toDomain() = NewsItem(
    title = title,
    description = description,
    author = author
)