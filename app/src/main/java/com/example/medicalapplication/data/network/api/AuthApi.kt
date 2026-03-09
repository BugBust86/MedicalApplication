package com.example.medicalapplication.data.network.api

import com.example.medicalapplication.data.network.model.LoginRequest
import com.example.medicalapplication.data.network.model.LoginResponse
import com.example.medicalapplication.data.network.model.RegisterRequest
import com.example.medicalapplication.data.network.model.RegisterResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

// 3. 创建网络 API 接口
interface AuthApi {
    @POST("user/login")
    // 挂起函数，参数自动转为json对象，该挂起函数返回的类型是 retrofit2.Response<RegisterResponse>
    suspend fun login(@Body request: LoginRequest): Response<LoginResponse>

    /** Response是Retrofit的包装类，包装类的数据部分T类型是 自定义的RegisterResponse
    如果返回结果赋给response，可通过以下方式访问响应体内容：
    response.isSuccessful - 判断 HTTP 请求是否成功
    response.body() - 获取 RegisterResponse 对象（可能为 null）
    response.message() - 获取 HTTP 状态消息
    response.code() - 获取 HTTP 状态码       */
    @POST("user/register")
    suspend fun register(@Body request: RegisterRequest): Response<RegisterResponse>



}