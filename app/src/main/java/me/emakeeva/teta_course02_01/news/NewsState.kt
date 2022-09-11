package me.emakeeva.teta_course02_01.news

sealed class NewsState {
    object Loading : NewsState()
    object Error : NewsState()
    class ShowNewsList(val data: List<NewsItem>) : NewsState()
}