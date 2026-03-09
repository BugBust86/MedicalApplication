package com.example.medicalapplication.ui.theme

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Shapes
import androidx.compose.ui.unit.dp

val Shapes = Shapes(
    // 小组件的圆角（如按钮、卡片）
    small = RoundedCornerShape(4.dp),

    // 中等组件的圆角（如卡片、对话框）
    medium = RoundedCornerShape(8.dp),

    // 大组件的圆角（如底部导航栏、大型卡片）
    large = RoundedCornerShape(16.dp),

    // 额外的大圆角（Material Design 3 新增）
    extraLarge = RoundedCornerShape(28.dp)
)
