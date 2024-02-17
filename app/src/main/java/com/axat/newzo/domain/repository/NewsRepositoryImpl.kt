package com.axat.newzo.domain.repository

import com.axat.newzo.data.model.NewsResponse
import com.axat.newzo.data.network.api.ApiService
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class NewsRepositoryImpl @Inject constructor(
    private val apiService: ApiService
) : NewsRepository {


    override suspend fun newsApi(domains: String, apiKey: String): NewsResponse {
        return apiService.getNews(domains, apiKey)
    }

    override suspend fun newsTopHeadlinesApi(country: String, apiKey: String): NewsResponse {
        return apiService.getNewsTopHeadlines(country, apiKey)
    }

}