package com.axat.newzo.ui.screens.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.FilterQuality
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.axat.newzo.R
import com.axat.newzo.data.model.Article


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DashboardScreen(
    modifier: Modifier = Modifier,
    viewModel: DashboardViewModel = hiltViewModel()
) {

    val dashboardUiState by viewModel.dashboardUiState.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.getNewsList("hindustantimes.com,indianexpress.com")
    }


    Scaffold (
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(text = "Newzo", textAlign = TextAlign.Center, fontFamily = FontFamily(Font(R.font.valofont)), overflow = TextOverflow.Ellipsis, color = Color.White)
                },
                colors = topAppBarColors(containerColor = Color.Red.copy(alpha = 0.6f))
            )
        },
        content = { paddingValue ->
            Column(
                modifier = modifier
                    .fillMaxSize()
                    .padding(paddingValue)
                    .padding(horizontal = 10.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                when (dashboardUiState) {

                    // Initial
                    DashboardScreenUiState.Initial -> Text(text = "Initial Time")

                    // Loading
                    DashboardScreenUiState.Loading -> CircularProgressIndicator(modifier = modifier.size(20.dp), color = Color.Red.copy(alpha = 0.6f))

                    // Error
                    is DashboardScreenUiState.Error -> Text(text = (dashboardUiState as DashboardScreenUiState.Error).message)

                    // Success
                    is DashboardScreenUiState.Success -> {

                        val newsUiState = dashboardUiState as DashboardScreenUiState.Success

                        LazyColumn{
                            items(newsUiState.news.articles) { article ->
                                ArticleItem(article)
                            }
                        }

                    }

                }

            }
        }
    )



}

@Composable
fun ArticleItem(
    article: Article,
    modifier: Modifier = Modifier
) {

    Spacer(modifier = modifier.height(10.dp))

    AsyncImage(
        ImageRequest.Builder(LocalContext.current)
            .data(article.urlToImage)
            .crossfade(true)
            .build(),
        contentDescription = null,
        filterQuality = FilterQuality.High,
        modifier = modifier.clip(RoundedCornerShape(12.dp))
    )

    Spacer(modifier = modifier.height(3.dp))

    Text(text = "♦ ${article.title}", style = TextStyle(fontSize = 16.sp, fontFamily = FontFamily(Font(R.font.montserrat_regular)), color = Color.Black, fontWeight = FontWeight.W600))

    Spacer(modifier = modifier.height(10.dp))

}