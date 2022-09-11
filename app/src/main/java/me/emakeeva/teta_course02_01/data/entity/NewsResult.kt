package me.emakeeva.teta_course02_01.data.entity

import com.google.gson.annotations.SerializedName

data class NewsResult(
    @SerializedName("status")
    val status: String,
    @SerializedName("articles")
    val articles: List<News>
)