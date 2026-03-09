package com.example.medicalapplication.data.network

import com.example.medicalapplication.data.network.api.AuthApi
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

// 定义全局唯一的单例对象，关闭app时销毁
object RetrofitInstance {
    // 服务器基础地址
    private const val BASE_URL = "http://192.168.148.24:8080"

    // 创建Retrofit并生成API接口实例
    val api: AuthApi by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(AuthApi::class.java)    // 获取AuthApi接口的Kotlin类引用，再转成java的Class对象
    }
}