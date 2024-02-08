package com.axat.newzo.data.network.api

import com.axat.newzo.data.model.NewsModel
import retrofit2.http.GET
import retrofit2.http.Query


interface ApiService {

    @GET("everything?")
    suspend fun getNews(
        @Query("domains") domain: String,
        @Query("apiKey") limit: String
    ): NewsModel

}