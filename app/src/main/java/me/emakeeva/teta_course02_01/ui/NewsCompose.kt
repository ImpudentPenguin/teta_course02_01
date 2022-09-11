package me.emakeeva.teta_course02_01.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import me.emakeeva.teta_course02_01.R
import me.emakeeva.teta_course02_01.news.NewsItem

private const val EMPTY = ""

@Composable
fun NewsList(data: List<NewsItem>, onRefresh: () -> Unit) {
    var isRefreshing by remember { mutableStateOf(false) }

    SwipeRefresh(
        state = rememberSwipeRefreshState(isRefreshing),
        onRefresh = {
            isRefreshing = true
            onRefresh.invoke()
        },
        modifier = Modifier.fillMaxSize()
    ) {
        LazyColumn(contentPadding = PaddingValues(top = 10.dp)) {
            items(data) { item ->
                News(item)
            }
        }
    }
}

@Composable
fun EmptyList(onUpdate: () -> Unit) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(
            text = stringResource(R.string.empty_news),
            textAlign = TextAlign.Center,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold
        )
        Button(onClick = { onUpdate.invoke() }) {
            Text(text = stringResource(R.string.repeat))
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ProgressBar() {
    Box(contentAlignment = Alignment.Center) {
        CircularProgressIndicator()
    }
}

@Composable
fun Error(onUpdate: () -> Unit) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = stringResource(R.string.error_state),
            textAlign = TextAlign.Center,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold
        )
        Button(onClick = { onUpdate.invoke() }) {
            Text(text = stringResource(R.string.repeat))
        }
    }
}

@Composable
private fun News(item: NewsItem) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp)
    ) {
        Text(
            text = item.title ?: EMPTY,
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold
        )
        Text(
            text = item.description ?: EMPTY,
            fontSize = 14.sp
        )
        Text(
            modifier = Modifier.fillMaxWidth(),
            text = item.author ?: EMPTY,
            textAlign = TextAlign.End,
            fontSize = 12.sp,
            fontStyle = FontStyle.Italic
        )

        Divider(modifier = Modifier.padding(vertical = 4.dp))
    }
}

@Preview(showBackground = true)
@Composable
private fun ErrorPreview() {
    Error {}
}

@Preview(showBackground = true)
@Composable
private fun EmptyListPreview() {
    EmptyList {}
}