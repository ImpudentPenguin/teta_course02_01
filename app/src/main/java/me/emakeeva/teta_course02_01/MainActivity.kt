package me.emakeeva.teta_course02_01

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import me.emakeeva.teta_course02_01.data.db.NewsLocalDataSource
import me.emakeeva.teta_course02_01.data.remote.NewsRemoteDataSource
import me.emakeeva.teta_course02_01.data.repository.NewsRepository
import me.emakeeva.teta_course02_01.news.NewsState
import me.emakeeva.teta_course02_01.news.NewsViewModel
import me.emakeeva.teta_course02_01.ui.EmptyList
import me.emakeeva.teta_course02_01.ui.Error
import me.emakeeva.teta_course02_01.ui.NewsList
import me.emakeeva.teta_course02_01.ui.ProgressBar
import me.emakeeva.teta_course02_01.ui.theme.Teta_course02_01Theme

open class MainActivity : ComponentActivity() {

    // TODO use viewModels() with DI
    private val viewModel by lazy {
        NewsViewModel(
            NewsRepository(
                NewsRemoteDataSource(),
                NewsLocalDataSource(applicationContext)
            )
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Teta_course02_01Theme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    when (val state = viewModel.state.collectAsState().value) {
                        is NewsState.Loading -> ProgressBar()
                        is NewsState.Error -> Error(viewModel::onRefresh)
                        is NewsState.ShowNewsList -> {
                            if (state.data.isEmpty()) EmptyList(viewModel::onUpdate)
                            else NewsList(state.data, viewModel::onUpdate)
                        }
                    }
                }
            }
        }
    }
}