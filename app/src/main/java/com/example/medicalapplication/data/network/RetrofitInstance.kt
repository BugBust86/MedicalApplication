package com.example.medicalapplication.data.network

import android.annotation.SuppressLint
import android.content.Context
import com.example.medicalapplication.data.network.api.AuthApi
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

@SuppressLint("StaticFieldLeak")
// 定义全局唯一的单例对象，关闭app时销毁
object RetrofitInstance {
    // 服务器基础地址
    private const val BASE_URL = "http://192.168.148.32:8080"

    private var context: Context? = null

    // 初始化 context（需要在 Application 中调用）
    fun init(context: Context) {
        this.context = context.applicationContext
    }

    // 创建 OkHttpClient，添加全局拦截器处理 401
    private val okHttpClient: OkHttpClient by lazy {
        val clientBuilder = OkHttpClient.Builder()
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)

        // 添加认证拦截器，处理 401 错误
        context?.let {
            clientBuilder.addInterceptor(AuthInterceptor(it))
        }

        clientBuilder.build()
    }

    // 创建Retrofit并生成API接口实例
    val api: AuthApi by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(AuthApi::class.java)    // 获取AuthApi接口的Kotlin类引用，再转成java的Class对象
    }
}