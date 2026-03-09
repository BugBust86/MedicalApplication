package com.example.medicalapplication.data.network.model

import com.google.gson.annotations.SerializedName

data class RegisterRequest(
    @SerializedName("phone")
    val phone: String,
    @SerializedName("psw")  // 发送请求时用的名字
    val password: String,   // 数据层的变量名
    @SerializedName("userName")
    val userName: String
)

data class RegisterResponse(
    @SerializedName("code")
    val code: Int,
    @SerializedName("data")
    val data: String,       // 注册成功时返回的data为null
    @SerializedName("message")
    val message: String     // 成功返回的消息时“注册成功”
)