package com.axat.newzo.data.model

import com.squareup.moshi.JsonClass


@JsonClass(generateAdapter = true)
data class NewsModel(
    val articles: List<Article>,
    val status: String,
    val totalResults: Int
)