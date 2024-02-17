package com.axat.newzo.domain.repository

import com.axat.newzo.data.model.NewsResponse

interface NewsRepository {

    suspend fun newsApi(domains: String, apiKey: String) : NewsResponse

    suspend fun newsTopHeadlinesApi(country: String, apiKey: String) : NewsResponse

}