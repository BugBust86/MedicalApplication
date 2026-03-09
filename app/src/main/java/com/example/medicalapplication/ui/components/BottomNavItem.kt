package com.example.medicalapplication.ui.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.MailOutline
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.MailOutline
import androidx.compose.material.icons.outlined.Person
import androidx.compose.runtime.saveable.Saver
import androidx.compose.runtime.saveable.listSaver
import androidx.compose.ui.graphics.vector.ImageVector

// 定义底部导航的密封类，记录底部三个导航对应的数据：路由、标题、选中时的按钮、为选中时的按钮
sealed class BottomNavItem(
    val route: String,
    val title: String,
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector
) {
    object Home : BottomNavItem(
        route = "home",
        title = "首页",
        selectedIcon = Icons.Filled.Home,
        unselectedIcon = Icons.Outlined.Home
    )

    object Message : BottomNavItem(
        route = "message",
        title = "消息",
        selectedIcon = Icons.Filled.MailOutline,
        unselectedIcon = Icons.Outlined.MailOutline
    )

    object Profile : BottomNavItem(
        route = "profile",
        title = "我的",
        selectedIcon = Icons.Filled.Person,
        unselectedIcon = Icons.Outlined.Person
    )

    companion object {
        // *为通配符，等同于任意类型，Any 也行
        val saver: Saver<BottomNavItem, *> = listSaver(
            save = { item ->
                listOf(item.route) // 仅保存路由（唯一标识BottomNavItem的核心字段）
            },
            // 优化3：增加边界校验+提取匹配逻辑，提升健壮性和可读性
            restore = { savedList ->
                // 兜底：若保存的List为空/无元素，直接返回Home
                val savedRoute = savedList.firstOrNull() ?: return@listSaver Home

                // 优化4：用“所有导航项的映射表”替代硬编码when，新增项时仅需加对象，无需改此处
                val allItems = listOf(Home, Message, Profile)
                allItems.firstOrNull { it.route == savedRoute } ?: Home
            }
        )
    }
}