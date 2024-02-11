package com.axat.newzo.domain.repository

import com.axat.newzo.data.model.NewsModel

interface NewsRepository {

    suspend fun newsApi(domains: String, apiKey: String) : NewsModel

}