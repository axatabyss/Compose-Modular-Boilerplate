package com.axat.newzo.ui.screens.home

import com.axat.newzo.data.model.NewsResponse

sealed interface DashboardScreenUiState {

    data object Initial : DashboardScreenUiState
    data object Loading : DashboardScreenUiState
    data class Success(val news: NewsResponse, val newsTopHeadlines: NewsResponse) : DashboardScreenUiState
    data class Error(val message: String) : DashboardScreenUiState

}