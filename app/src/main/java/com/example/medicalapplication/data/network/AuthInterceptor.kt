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

    // OkHttp Interceptor 接口的核心方法，chain 是请求链，可以理解为请求和响应的传递者
    // 该方法会拦截每一个 HTTP 的请求和响应
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()   // 从请求链中获取原始的 http 对象
        val response = chain.proceed(request)   // 将请求传给下一个拦截器，或者最终发送到服务器

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
