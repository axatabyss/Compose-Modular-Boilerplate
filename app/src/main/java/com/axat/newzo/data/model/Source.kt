package com.axat.newzo.data.model

import com.squareup.moshi.JsonClass


@JsonClass(generateAdapter = true)
data class Source(
    val id: Any,
    val name: String
)