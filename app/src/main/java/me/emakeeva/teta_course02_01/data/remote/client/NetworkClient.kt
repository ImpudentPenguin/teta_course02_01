package me.emakeeva.teta_course02_01.data.remote.client

import me.emakeeva.teta_course02_01.data.remote.api.NewsApi
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object NetworkClient {

    fun createApi(): NewsApi {
        return Retrofit.Builder()
            .baseUrl("https://newsapi.org/v2/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(
                OkHttpClient.Builder()
                    .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
                    .addInterceptor(Interceptor { chain ->
                        val request = chain.request().newBuilder()
                            .url(chain.request().url.newBuilder().addQueryParameter("apiKey", "e145667a0b4547f69c0535a734a21627").build())
                            .build()
                        chain.proceed(request)
                    })
                    .build()
            )
            .build()
            .create(NewsApi::class.java)
    }
}