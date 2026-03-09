package com.example.medicalapplication

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.example.medicalapplication.ui.screens.MainScreen
import com.example.medicalapplication.viewmodel.AuthViewModel

// 根组件，根据登录状态（token是否失效），决定是导航到认证Auth组件，还是导航到主页Main组件
// Main组件也是各个下方导航栏对应组件的根组件
@Composable    // 接收是否登录成功的状态，如果成功则转为MainScreen
fun SmartHealthApp(authViewModel: AuthViewModel) {
     // 收集登录成功的状态
     val loginSuccess by authViewModel.loginSuccess.collectAsState()

//     if (loginSuccess){
//          MainScreen()
//     } else {
//          LoginRegisterScreen(
//               viewModel = authViewModel
//          )
//     }
     MainScreen()
}