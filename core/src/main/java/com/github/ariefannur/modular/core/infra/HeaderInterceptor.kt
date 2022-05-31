package com.github.ariefannur.modular.core.infra

import com.github.ariefannur.modular.core.BuildConfig
import okhttp3.Interceptor
import okhttp3.Response

class HeaderInterceptor: Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request().newBuilder()
            .addHeader("Authorization", "token ${BuildConfig.TOKEN}")
            .build()
        return chain.proceed(request)
    }
}