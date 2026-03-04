package com.example.medicalapplication

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.medicalapplication.ui.components.MedicalTopBar
import com.example.medicalapplication.ui.screens.LoginRegisterScreen

// 根组件，根据登录状态（token是否失效），决定是导航到认证Auth组件，还是导航到主页Main组件
// Main组件也是各个下方导航栏对应组件的根组件
@Composable
fun SmartHealthApp() {
    // 暂定登录页面
    Scaffold(
        topBar = { MedicalTopBar() }
    ) { // innerPadding的实际值是Scaffold函数根据topBar、bottomBar等测出来的，防止内容区域与上下bar重合，
        // 变量名叫什么都可以，一般为了可读性，叫innerPadding
            innerPadding->
        // 有.padding(innerPadding)的内边距，不会重合
        LoginRegisterScreen(modifier = Modifier.padding(innerPadding))
    }
}