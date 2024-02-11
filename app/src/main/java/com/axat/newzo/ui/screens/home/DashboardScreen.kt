package com.axat.newzo.ui.screens.home

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.axat.newzo.data.model.Article


@SuppressLint("ResourceType")
@Composable
fun DashboardScreen(
    modifier: Modifier = Modifier,
    viewModel: DashboardViewModel = hiltViewModel()
) {

    val dashboardUiState by viewModel.dashboardUiState.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.getNewsList("hindustantimes.com,indianexpress.com")
    }


    Scaffold { padding ->
        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(padding),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {


            when (dashboardUiState) {

                // Initial
                DashboardScreenUiState.Initial -> Text(text = "Initial Time")

                // Loading
                DashboardScreenUiState.Loading -> CircularProgressIndicator()

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



}

@Composable
fun ArticleItem(article: Article) {
    Text(text = article.title)
}