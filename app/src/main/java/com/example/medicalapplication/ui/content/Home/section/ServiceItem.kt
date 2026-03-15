package com.example.medicalapplication.ui.content.Home.section

import androidx.compose.ui.graphics.Color
import com.example.medicalapplication.R

data class ServiceItem(
    val drawableRes: Int,
    val label: String,
    val backgroundColor: Color,
    val route: String,
    val onClick: () -> Unit
)

val outpatientItems = listOf(
    ServiceItem(
        drawableRes = R.drawable.register_files_white,
        label = "注册建档",
        backgroundColor = Color(0xFFF59E0B),
        route = "",
        onClick = { }
    ),
    ServiceItem(
        drawableRes = R.drawable.appointment,
        label = "预约挂号",
        backgroundColor = Color(0xFF3B82F6),     // 经典蓝色
        route = "",
        onClick = { }
    ),
    ServiceItem(
        drawableRes = R.drawable.outpatient_report,
        label = "我的预约",
        backgroundColor = Color(0xFF10B981),    // 翠绿色
        route = "",
        onClick = { }
    ),
    ServiceItem(
        drawableRes = R.drawable.outpatient_payment,
        label = "门诊缴费",
        backgroundColor = Color(0xFFF59E0B),    // 琥珀色
        route = "",
        onClick = { }
    ),
    ServiceItem(
        drawableRes = R.drawable.medical_report,
        label = "问诊报告",
        backgroundColor = Color(0xFF6366F1),    // 紫罗兰色
        route = "",
        onClick = { }
    ),
    ServiceItem(
        drawableRes = R.drawable.medical_record,
        label = "就诊记录",
        backgroundColor = Color(0xFFEC4899),
        route = "",
        onClick = { }
    ),
)

val checkoutItems = listOf(
    ServiceItem(
        drawableRes = R.drawable.check_appontment,
        label = "检查化验预约",
        backgroundColor = Color(0xFFFFFFFF),
        route = "",
        onClick = { }
    ),
    ServiceItem(
        drawableRes = R.drawable.my_appointment,
        label = "我的检查预约",
        backgroundColor = Color(0xFF14B8A6), // 青色 - 冷静、清晰
        route = "",
        onClick = { }
    ),
    ServiceItem(
        drawableRes = R.drawable.check_result,
        label = "检验结果",
        backgroundColor = Color(0xFF06B6D4), // 天蓝色 - 清爽、专业
        route = "",
        onClick = { }
    ),
    ServiceItem(
        drawableRes = R.drawable.check_payment,
        label = "检验缴费",
        backgroundColor = Color(0xFFF97316), // 橙色 - 活力、行动
        route = "",
        onClick = { }
    ),
    ServiceItem(
        drawableRes = R.drawable.check_record,
        label = "检查记录",
        backgroundColor = Color(0xFF84CC16), // 黄绿色 - 结果、完成
        route = "",
        onClick = { }
    ),
)

val convenientItems = listOf(
    ServiceItem(
        drawableRes = R.drawable.medical_guide1,
        label = "就医指南",
        backgroundColor = Color(0xFF06B6D4), // 天蓝色 - 清爽、专业
        route = "",
        onClick = { }
    ),
    ServiceItem(
        drawableRes = R.drawable.hospital_introduce,
        label = "医院介绍",
        backgroundColor = Color(0xFF6366F1), // 靛蓝色 - 一致性强
        route = "",
        onClick = { }
    ),
    ServiceItem(
        drawableRes = R.drawable.medical_safe_card,
        label = "医保卡管理",
        backgroundColor = Color(0xFF10B981), // 翠绿色 - 保障、安全
        route = "",
        onClick = { }
    ),
    ServiceItem(
        drawableRes = R.drawable.medical_card,
        label = "就诊卡管理",
        backgroundColor =  Color(0xFF8B5CF6), // 紫罗兰色 - 尊贵、重要
        route = "",
        onClick = { }
    ),
    ServiceItem(
        drawableRes = R.drawable.health_manage,
        label = "健康管理",
        backgroundColor = Color(0xFFEC4899), // 粉红色 - 关爱、健康
        route = "",
        onClick = { }
    ),
)
