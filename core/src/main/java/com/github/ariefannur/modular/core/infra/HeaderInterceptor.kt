package com.github.ariefannur.modular.core.infra

import okhttp3.Interceptor
import okhttp3.Response

class HeaderInterceptor: Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request().newBuilder()
            .addHeader("Authorization", "token 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")
            .build()
        return chain.proceed(request)
    }
}