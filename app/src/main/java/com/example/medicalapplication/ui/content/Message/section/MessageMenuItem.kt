package com.example.medicalapplication.ui.content.Message.section

import androidx.compose.ui.graphics.Color
import com.example.medicalapplication.R
import com.example.medicalapplication.ui.screens.MessageRoutes

data class MessageMenuItem(
    val drawableRes: Int,
    val label: String,
    val backgroundColor: Color,
    val route: String,
    val onClick: () -> Unit
)

val messageMenuItems = listOf(
    MessageMenuItem(
        drawableRes = R.drawable.hospital_message,
        label = "医院动态",
        backgroundColor = Color(0xFF3B82F6),
        route = MessageRoutes.HOSPITAL_DYNAMIC,
        onClick = { }
    ),
    MessageMenuItem(
        drawableRes = R.drawable.my_message,
        label = "我的消息",
        backgroundColor = Color(0xFF10B981),
        route = MessageRoutes.MY_MESSAGE,
        onClick = { }
    ),
    MessageMenuItem(
        drawableRes = R.drawable.consult_message,
        label = "问诊消息",
        backgroundColor = Color(0xFFF59E0B),
        route = MessageRoutes.CONSULT_MESSAGE,
        onClick = { }
    ),
    MessageMenuItem(
        drawableRes = R.drawable.disease_science,
        label = "专病科普",
        backgroundColor = Color(0xFF6366F1),
        route = MessageRoutes.DISEASE_SCIENCE,
        onClick = { }
    ),
)
