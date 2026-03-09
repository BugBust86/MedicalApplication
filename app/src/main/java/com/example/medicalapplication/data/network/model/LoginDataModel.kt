package com.example.medicalapplication.data.network.model

import com.google.gson.annotations.SerializedName

// 2. 创建数据模型类
// 登录请求体
data class LoginRequest(
    @SerializedName("phone")
    val phone: String,
    @SerializedName("psw")
    val password: String
)
// 登录响应体
data class LoginResponse(
    @SerializedName("code")
    val code: Int,
    @SerializedName("data")
    val token: String,
    @SerializedName("message")
    val message: String
)
