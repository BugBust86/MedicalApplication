package com.example.medicalapplication.ui.content.Profile.section

import androidx.compose.ui.graphics.Color
import com.example.medicalapplication.ui.screens.ProfileRoutes

data class ProfileMenuItem(
    val label: String,
    val backgroundColor: Color,
    val route: String,
    val onClick: () -> Unit
)

val profileMenuItems = listOf(
    ProfileMenuItem(
        label = "就诊卡管理",
        backgroundColor = Color(0xFF3B82F6),
        route = ProfileRoutes.MEDICAL_CARD,
        onClick = { }
    ),
    ProfileMenuItem(
        label = "医保卡管理",
        backgroundColor = Color(0xFF10B981),
        route = ProfileRoutes.INSURANCE_CARD,
        onClick = { }
    ),
    ProfileMenuItem(
        label = "问诊记录",
        backgroundColor = Color(0xFFF59E0B),
        route = ProfileRoutes.CONSULTATION_RECORD,
        onClick = { }
    ),
)
