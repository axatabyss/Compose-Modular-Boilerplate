package com.axat.newzo.ui.screens.home

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.axat.newzo.BuildConfig
import com.axat.newzo.domain.repository.NewsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class DashboardViewModel @Inject constructor(
    private val newsRepository: NewsRepository
) : ViewModel() {


    private val _dashboardUiState = MutableSharedFlow<DashboardScreenUiState>()
    val dashboardUiState: SharedFlow<DashboardScreenUiState> = _dashboardUiState.asSharedFlow()



    fun getNewsList(domains: String, country: String) {
        viewModelScope.launch (Dispatchers.IO) {

            _dashboardUiState.emit(DashboardScreenUiState.Loading)

            try {

                val response = newsRepository.newsApi(domains, BuildConfig.API_KEY)
                val topHeadlinesResponse = newsRepository.newsTopHeadlinesApi(country, BuildConfig.API_KEY)

                _dashboardUiState.emit(DashboardScreenUiState.Success(response, topHeadlinesResponse))

            } catch (e: Exception) {

                val errorMessage = "Failed to fetch news: ${e.message}"
                _dashboardUiState.emit(DashboardScreenUiState.Error(errorMessage))
                Log.e("DashboardViewModel", errorMessage)

            }

        }
    }



}