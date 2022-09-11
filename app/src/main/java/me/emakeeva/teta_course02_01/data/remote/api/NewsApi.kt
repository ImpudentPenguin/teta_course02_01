package me.emakeeva.teta_course02_01.data.remote.api

import me.emakeeva.teta_course02_01.data.entity.NewsResult
import retrofit2.http.GET

interface NewsApi {
    @GET("top-headlines?country=ru&pageSize=10")
    suspend fun getNews(): NewsResult
}