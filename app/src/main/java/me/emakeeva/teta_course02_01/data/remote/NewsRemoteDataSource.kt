package me.emakeeva.teta_course02_01.data.remote

import me.emakeeva.teta_course02_01.data.entity.NewsResult
import me.emakeeva.teta_course02_01.data.entity.runOperationCatching
import me.emakeeva.teta_course02_01.data.remote.client.NetworkClient
import me.emakeeva.teta_course02_01.data.entity.Result

class NewsRemoteDataSource {
    suspend fun getNews(): Result<NewsResult, Throwable> {
        return runOperationCatching {
            NetworkClient.createApi().getNews()
        }
    }
}