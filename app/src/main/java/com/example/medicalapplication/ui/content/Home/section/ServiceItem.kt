package com.example.medicalapplication.ui.content.Home.section

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Person
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector

// 单个图标的数据模型，包含图标、标签、背景颜色、路由、点击回调函数
data class ServiceItem(
    val icon: ImageVector,
    val label: String,
    val backgroundColor: Color,
    val route: String,
    val onClick: () -> Unit
)
// 门诊服务区块的使用示例
val outpatientItems = listOf(
    ServiceItem(
        icon = Icons.Filled.Person,
        label = "注册建档",     //注册就诊卡
        backgroundColor = Color(0xFFF59E0B),  // 橙色
        route = "",
        onClick = { /* 跳转到注册建档页面 */ }
    ),
    ServiceItem(
        icon = Icons.Filled.Notifications,
        label = "预约挂号",
        backgroundColor = Color(0xFF3B82F6),  // 蓝色
        route = "",
        onClick = { /* 跳转到门诊报到页面 */ }
    ),
    ServiceItem(
        icon = Icons.Filled.Notifications,
        label = "我的预约",
        backgroundColor = Color(0xFF3B82F6),  // 蓝色
        route = "",
        onClick = { /* 跳转到门诊报到页面 */ }
    ),
    ServiceItem(
        icon = Icons.Filled.Notifications,
        label = "门诊缴费",
        backgroundColor = Color(0xFF3B82F6),  //
        route = "",
        onClick = { /* 跳转到门诊缴费页面 */ }
    ),
    ServiceItem(
        icon = Icons.Filled.Notifications,
        label = "检验报告",
        backgroundColor = Color(0xFF3B82F6),  // 蓝色
        route = "",
        onClick = { /* 跳转到门诊报到页面 */ }
    ),
    ServiceItem(
        icon = Icons.Filled.Notifications,
        label = "就诊记录",
        backgroundColor = Color(0xFF3B82F6),  // 蓝色
        route = "",
        onClick = { /* 跳转到门诊报到页面 */ }
    ),

)

//