package com.axat.newzo.data.network.interceptor

import okhttp3.Interceptor
import okhttp3.Response
import kotlin.random.Random


class RequestInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()

        val timestamp = Random.nextInt(10000, 99999).toString()

        val url = request.url.newBuilder()
            .addQueryParameter("ts", timestamp)
            .build()

        val newRequest = request.newBuilder().url(url).build()
        return chain.proceed(newRequest)
    }
}