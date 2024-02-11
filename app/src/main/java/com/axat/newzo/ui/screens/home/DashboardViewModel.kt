package com.axat.newzo.ui.screens.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.axat.newzo.BuildConfig
import com.axat.newzo.domain.repository.NewsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class DashboardViewModel @Inject constructor(
    private val newsRepository: NewsRepository
) : ViewModel() {


    private val _dashboardUiState = MutableStateFlow<DashboardScreenUiState>(DashboardScreenUiState.Initial)
    val dashboardUiState: StateFlow<DashboardScreenUiState> get() = _dashboardUiState


    fun getNewsList(domains: String) {
        viewModelScope.launch (Dispatchers.Main) {

            _dashboardUiState.value = DashboardScreenUiState.Loading

            try {

                val response = newsRepository.newsApi(domains, BuildConfig.API_KEY)

                response.let {
                    _dashboardUiState.value = DashboardScreenUiState.Success(it)
                }

            } catch (e: Exception) {
                if (BuildConfig.DEBUG) {
                    _dashboardUiState.value = DashboardScreenUiState.Error("${e.message}")
                }
            }

        }
    }



}