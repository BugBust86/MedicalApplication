package com.example.medicalapplication.ui.components

import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

@Composable
fun BottomNavigationBar(
    items: List<BottomNavItem>,    // 传入的底部导航项列表
    selectedItem: BottomNavItem,    // 选择的底部导航数据项
    onItemSelected: (BottomNavItem) -> Unit     // 选择之后调用的函数
){
    NavigationBar {
        // items 是列表，foreach 是它的扩展函数，扩展函数的参数是仅有一个 lambda 表达式，所以小括号省略了
        items.forEach { item->
            NavigationBarItem(
                selected = selectedItem ==item,
                onClick = { onItemSelected(item) },
                icon = {    // 该参数需要一个图标 @Composable的可组合函数
                    Icon(
                        imageVector = if (selectedItem == item) item.selectedIcon else item.unselectedIcon,
                        contentDescription = item.title
                    )
                },
                label = { Text(item.title) }
            )
        }
    }
}