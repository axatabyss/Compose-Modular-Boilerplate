package com.axat.newzo.data.model


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Source(
    @Json(name = "id")
    val id: Any? = null,
    @Json(name = "name")
    val name: String = ""
)