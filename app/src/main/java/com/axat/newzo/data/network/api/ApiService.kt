package com.axat.newzo.data.network.api

import com.axat.newzo.data.model.NewsResponse
import retrofit2.http.GET
import retrofit2.http.Query


interface ApiService {

    @GET("everything")
    suspend fun getNews(
        @Query("domains") domain: String,
        @Query("apiKey") apiKey: String
    ): NewsResponse


    @GET("top-headlines")
    suspend fun getNewsTopHeadlines(
        @Query("country") country: String,
        @Query("apiKey") apiKey: String
    ): NewsResponse

}