package com.example.medicalapplication

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.example.medicalapplication.ui.screens.LoginRegisterScreen
import com.example.medicalapplication.ui.screens.MainScreen
import com.example.medicalapplication.viewmodel.AuthViewModel

// 根组件，根据登录状态（token是否失效），决定是导航到认证Auth组件，还是导航到主页Main组件
// Main组件也是各个下方导航栏对应组件的根组件
@Composable    // 接收是否登录成功的状态，如果成功则转为MainScreen
fun SmartHealthApp(authViewModel: AuthViewModel) {
     // 收集自动登录状态（检查 token 是否在有效期内）
     val isLoggedIn by authViewModel.isLoggedIn.collectAsState()
     // 收集登录成功的状态（用于手动登录成功后更新状态）
     val loginSuccess by authViewModel.loginSuccess.collectAsState()
     // 收集 token 失效状态（用于 401 时自动跳转）
     val tokenExpired by authViewModel.tokenExpired.collectAsState()

     // token 失效时重置状态并显示登录页
     LaunchedEffect(tokenExpired) {
         if (tokenExpired) {
             // 状态已更新，下次重组会自动显示登录页
         }
     }

     // 如果 token 有效或登录成功，则显示主页
     if ((isLoggedIn || loginSuccess) && !tokenExpired){
          MainScreen(authViewModel = authViewModel)
     } else {
          LoginRegisterScreen(
               viewModel = authViewModel
          )
     }
}