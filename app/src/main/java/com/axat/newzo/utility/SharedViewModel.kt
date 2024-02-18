package com.axat.newzo.utility

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.axat.newzo.data.model.Article
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject


@HiltViewModel
class SharedViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {



    private val _sharedState = MutableStateFlow(Article())
    val sharedState = _sharedState.asStateFlow()

    fun updateState(article: Article) {
        _sharedState.value = article
    }



}