package me.emakeeva.teta_course02_01.data.repository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import me.emakeeva.teta_course02_01.data.db.NewsLocalDataSource
import me.emakeeva.teta_course02_01.data.entity.News
import me.emakeeva.teta_course02_01.data.remote.NewsRemoteDataSource
import me.emakeeva.teta_course02_01.data.entity.Result

class NewsRepository(
    private val newsRemoteDataSource: NewsRemoteDataSource,
    private val newsLocalDataSource: NewsLocalDataSource
) {
    suspend fun getNews(): Flow<Result<List<News>, Throwable>> {
        return flow {
            val local = newsLocalDataSource.getNews()
            if (local is Result.Success && local.data.isNotEmpty()) {
                emit(local)
            } else {
                val remote = newsRemoteDataSource.getNews()
                if (remote is Result.Success) {
                    newsLocalDataSource.insertNews(remote.data.articles)
                    emit(Result.Success(remote.data.articles))
                } else {
                    emit(Result.Error(NewsErrorException()))
                }
            }
        }
    }

    suspend fun refreshNews(): Flow<Result<List<News>, Throwable>> {
        return flow {
            val remote = newsRemoteDataSource.getNews()
            if (remote is Result.Success) {
                newsLocalDataSource.insertNews(remote.data.articles)
                emit(Result.Success(remote.data.articles))
            } else {
                emit(Result.Error(NewsErrorException()))
            }
        }
    }
}

class NewsErrorException : Exception()