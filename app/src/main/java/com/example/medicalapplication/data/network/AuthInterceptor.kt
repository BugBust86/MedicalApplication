package com.example.medicalapplication.data.network

import android.content.Context
import com.example.medicalapplication.data.local.TokenManager
import kotlinx.coroutines.runBlocking
import okhttp3.Interceptor
import okhttp3.Response
import java.net.HttpURLConnection

/**
 * 全局网络拦截器，统一处理 401 认证失败
 */
class AuthInterceptor(private val context: Context) : Interceptor {

    // 使用单例模式的 TokenManager，避免重复创建
    private val tokenManager: TokenManager by lazy {
        TokenManager.getInstance(context)
    }

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val response = chain.proceed(request)

        // 如果返回 401，清除 token 并通知 token 失效
        if (response.code == HttpURLConnection.HTTP_UNAUTHORIZED) {
            response.close()
            runBlocking {
                tokenManager.notifyTokenExpired()
            }
        }

        return response
    }
}
