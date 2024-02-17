package com.axat.newzo.ui.screens.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.DateRange
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.FilterQuality
import androidx.compose.ui.layout.ContentScale
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

    var dashboardUiState by remember {
        mutableStateOf<DashboardScreenUiState>(DashboardScreenUiState.Initial)
    }

    LaunchedEffect(Unit) {
        viewModel.getNewsList("hindustantimes.com,indianexpress.com", "in")
        viewModel.dashboardUiState.collect { state ->
            dashboardUiState = state
        }
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
                    .padding(paddingValue),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.Start
            ) {

                when (dashboardUiState) {

                    // Initial
                    DashboardScreenUiState.Initial -> {
                        Box(
                            contentAlignment = Alignment.Center,
                            modifier = modifier.align(alignment = Alignment.CenterHorizontally)
                        ) {
                            Text(text = "Initial Time")
                        }
                    }

                    // Loading
                    DashboardScreenUiState.Loading -> {
                        Box(
                            contentAlignment = Alignment.Center,
                            modifier = modifier.align(alignment = Alignment.CenterHorizontally)
                        ) {
                            CircularProgressIndicator(modifier = modifier.size(20.dp), color = Color.Red.copy(alpha = 0.6f))
                        }
                    }

                    // Error
                    is DashboardScreenUiState.Error -> {
                        Box(
                            contentAlignment = Alignment.Center,
                            modifier = modifier.align(alignment = Alignment.CenterHorizontally)
                        ) {
                            Text(text = (dashboardUiState as DashboardScreenUiState.Error).message)
                        }
                    }

                    // Success
                    is DashboardScreenUiState.Success -> {

                        val newsUiState = dashboardUiState as DashboardScreenUiState.Success



                        Spacer(modifier = modifier.height(20.dp))

                        Text(
                            text = "Top Headlines",
                            textAlign = TextAlign.Start,
                            style = TextStyle(
                                fontSize = 18.sp,
                                fontFamily = FontFamily(Font(R.font.montserrat_regular)),
                                color = Color.Black,
                                fontWeight = FontWeight.W600
                            ),
                            modifier = modifier.padding(horizontal = 5.dp)
                        )

                        LazyRow {
                            items(newsUiState.newsTopHeadlines.articles) { article ->
                                NewsTopHeadlinesItem(article)
                            }
                        }

                        Spacer(modifier = modifier.height(20.dp))

                        LazyColumn {
                            items(newsUiState.news.articles) { article ->
                                NewsItem(article)
                            }
                        }

                    }

                }

            }
        }
    )



}

@Composable
fun NewsTopHeadlinesItem(
    article: Article,
    modifier: Modifier = Modifier
) {

    Box(
        modifier = modifier
            .width(280.dp)
            .padding(horizontal = 10.dp)
    ) {

        Column {

            Spacer(modifier = modifier.height(10.dp))

            AsyncImage(
                ImageRequest.Builder(LocalContext.current)
                    .data(article.urlToImage ?: "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQWTMaowVw4ABlJ5di42VwbCJuVVGay40W8otrBe_v-HA&s")
                    .crossfade(true)
                    .build(),
                contentDescription = null,
                filterQuality = FilterQuality.High,
                contentScale = ContentScale.FillHeight,
                modifier = modifier
                    .clip(RoundedCornerShape(18.dp))
                    .height(200.dp)
            )

            Spacer(modifier = modifier.height(5.dp))

            Text(text = "${article.title}",
                overflow = TextOverflow.Ellipsis,
                maxLines = 2,
                style = TextStyle(fontSize = 12.sp, fontFamily = FontFamily(Font(R.font.montserrat_regular)), color = Color.Black, fontWeight = FontWeight.W600),
                modifier = modifier.padding(5.dp)
            )

        }

    }

}

@Composable
fun NewsItem(
    article: Article,
    modifier: Modifier = Modifier
) {

    Row(modifier = modifier
        .fillMaxWidth(1f)
        .padding(horizontal = 10.dp, vertical = 10.dp)) {

        AsyncImage(
            ImageRequest.Builder(LocalContext.current)
                .data(article.urlToImage ?: "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQWTMaowVw4ABlJ5di42VwbCJuVVGay40W8otrBe_v-HA&s")
                .crossfade(true)
                .build(),
            contentDescription = null,
            filterQuality = FilterQuality.High,
            contentScale = ContentScale.FillHeight,
            modifier = modifier
                .height(80.dp)
                .width(80.dp)
                .clip(RoundedCornerShape(16.dp))
        )

        Spacer(modifier = modifier.width(10.dp))

        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.Start
        ) {

            Text(text = "${article.title}",
                maxLines = 2,
                style = TextStyle(fontSize = 12.sp, fontFamily = FontFamily(Font(R.font.montserrat_regular)), color = Color.Black, fontWeight = FontWeight.Bold)
            )

            Spacer(modifier = modifier.height(25.dp))

            Row(
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(Icons.Outlined.DateRange, contentDescription = null, modifier = modifier.size(15.dp), tint = Color.Gray)
                Spacer(modifier = modifier.width(5.dp))
                Text(text = "${article.publishedAt}", style = TextStyle(fontSize = 10.sp, fontFamily = FontFamily(Font(R.font.montserrat_regular)), color = Color.Gray, fontWeight = FontWeight.W500))
            }

        }

    }

}