package me.emakeeva.teta_course02_01.news

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import me.emakeeva.teta_course02_01.data.entity.doOnError
import me.emakeeva.teta_course02_01.data.entity.doOnSuccess
import me.emakeeva.teta_course02_01.data.entity.toDomain
import me.emakeeva.teta_course02_01.data.repository.NewsRepository

open class NewsViewModel(private val repository: NewsRepository) : ViewModel() {
    private val _state: MutableStateFlow<NewsState> = MutableStateFlow(NewsState.Loading)
    val state = _state.asStateFlow()

    init {
        loadNews()
    }

    fun onUpdate() {
        viewModelScope.launch {
            _state.emit(NewsState.Loading)
            repository.refreshNews().collect { result ->
                result.doOnSuccess { news ->
                    val items = news.map { it.toDomain() }
                    _state.emit(NewsState.ShowNewsList(items))
                }

                result.doOnError {
                    _state.emit(NewsState.Error)
                }
            }
        }
    }

    fun onRefresh() {
        loadNews()
    }

    private fun loadNews() {
        viewModelScope.launch {
            _state.emit(NewsState.Loading)
            repository.getNews().collect { result ->
                result.doOnSuccess { news ->
                    val items = news.map { it.toDomain() }
                    _state.emit(NewsState.ShowNewsList(items))
                }

                result.doOnError {
                    _state.emit(NewsState.Error)
                }
            }
        }
    }
}