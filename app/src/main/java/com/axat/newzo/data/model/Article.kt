package com.axat.newzo.data.model


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Article(
    @Json(name = "author")
    val author: String? = "",
    @Json(name = "content")
    val content: String? = null,
    @Json(name = "description")
    val description: String? = null,
    @Json(name = "publishedAt")
    val publishedAt: String? = null,
    @Json(name = "source")
    val source: Source = Source(),
    @Json(name = "title")
    val title: String? = null,
    @Json(name = "url")
    val url: String? = null,
    @Json(name = "urlToImage")
    val urlToImage: String? = null
)