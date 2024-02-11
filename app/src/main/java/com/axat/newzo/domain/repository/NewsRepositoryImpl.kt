package com.axat.newzo.domain.repository

import com.axat.newzo.data.model.NewsModel
import com.axat.newzo.data.network.api.ApiService
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class NewsRepositoryImpl @Inject constructor(
    private val apiService: ApiService
) : NewsRepository {


    override suspend fun newsApi(domains: String, apiKey: String): NewsModel {
        return apiService.getNews(domains, apiKey)
    }

}