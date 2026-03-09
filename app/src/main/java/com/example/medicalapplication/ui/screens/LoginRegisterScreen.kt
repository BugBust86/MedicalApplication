package com.example.medicalapplication.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import com.example.medicalapplication.ui.components.MedicalTopBar
import com.example.medicalapplication.viewmodel.AuthViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
// 登录注册页面的内容区
fun LoginRegisterContent(
    modifier: Modifier = Modifier,
    viewModel: AuthViewModel,   // 获取viewModel，移除默认值，必须传参
//    onLoginSuccess: () -> Unit     // 登录成功的回调函数交给父组件设置
){
    // 定义状态
    // 必须要mutableStateOf，因为直接var isLogin=true只会被当普通变量，不被compose知晓，不会重组组件
    // 必须remember，因为compose组件重组组件时，代码从上往下执行，再执行一次var isLogin by mutableStateOf(true)相当于又把isLogin的值重置了，页面还是不会变化
    // by是语法糖，本质是get、set value
//    var isLoginMode by remember { mutableStateOf(true) }    // true登录，false=注册，默认为登录
//    var account by remember { mutableStateOf("") }      // “” 记录手机号输入框的初始输入数据
//    var password by remember { mutableStateOf("") }
//    var confirmPassword by remember { mutableStateOf("") }
    // 使用 StateFlow 的 collectAsState 转换为 Compose State
    val isLoginMode by viewModel.isLoginMode.collectAsState()
    val account by viewModel.account.collectAsState()       // collectAsState是StateFlow的扩展函数
    val password by viewModel.password.collectAsState()
    val confirmPassword by viewModel.confirmPassword.collectAsState()
    val userName by viewModel.userName.collectAsState()
    val isLoading by viewModel.isLoading.collectAsState()
    val errorMessage by viewModel.errorMessage.collectAsState()

    val snackbarHostState = remember { SnackbarHostState() }
//    val scope = rememberCoroutineScope()

    LaunchedEffect(errorMessage) {
        errorMessage?.let { error ->
            snackbarHostState.showSnackbar(error)   // 底部的灰色错误提示条
            viewModel.clearError()  // 清空错误信息
        }
    }

    Scaffold(
        snackbarHost = { SnackbarHost(snackbarHostState) }
    ) { innerPadding ->
        Column (
            modifier = modifier
                .fillMaxSize()
                .padding(16.dp)
                .padding(innerPadding),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ){
            Text(
                text = if (isLoginMode) "欢迎登录" else "创建账号",
                style = MaterialTheme.typography.headlineMedium
            )
            Spacer(modifier = Modifier.height(24.dp))
            OutlinedTextField(
                value = account,
                onValueChange = { viewModel.updateAccount(it) },
                label = { Text("请输入手机号") },
                singleLine = true,
                modifier = Modifier.wrapContentWidth(),
                enabled = !isLoading,   // isLoading为初始值false的时候可以输入，为true，即执行登录函数时锁定，防止重复发送请求
                isError = errorMessage != null && account.isNotEmpty()  // 当错误消息不为空，手机号未填写时
            )
            if (!isLoginMode){
                Spacer(modifier = Modifier.height(8.dp))
                OutlinedTextField(
                    value = userName,
                    onValueChange = { viewModel.updateUserName(it) },
                    label = { Text("用户名")},
                    singleLine = true,
                    modifier = Modifier.wrapContentWidth(),
                    enabled = !isLoading,
                    isError = errorMessage != null && confirmPassword.isNotEmpty()
                )
            }
            Spacer(modifier = Modifier.height(8.dp))
            OutlinedTextField(
                value = password,
                onValueChange = { viewModel.updatePassword(it) },
                label = {Text("密码")},
                singleLine = true,
                visualTransformation = PasswordVisualTransformation(),
                modifier = Modifier.wrapContentWidth(),
                enabled = !isLoading,
                isError = errorMessage != null && password.isNotEmpty()
            )

            if (!isLoginMode){
                Spacer(modifier = Modifier.height(8.dp))
                OutlinedTextField(
                    value = confirmPassword,
                    onValueChange = { viewModel.updateConfirmPassword(it) },
                    label = { Text("确认密码")},
                    singleLine = true,
                    visualTransformation = PasswordVisualTransformation(),
                    modifier = Modifier.wrapContentWidth(),
                    enabled = !isLoading,
                    isError = errorMessage != null && confirmPassword.isNotEmpty()
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = {
                    if (isLoginMode) {
                        viewModel.login()   // 登录模式点击按钮调用viewModel的登录函数
                    } else {
                        viewModel.register()   // 非登录模式调用注册函数
                    }
                },
                modifier = Modifier.fillMaxWidth(0.8f),
                enabled = !isLoading
            ) {
                if (isLoading) {    //按钮正在加载时显示的旋转圈圈
                    CircularProgressIndicator(
                        modifier = Modifier.height(20.dp),
                        color = MaterialTheme.colorScheme.onPrimary
                    )
                } else {    // 否则显示文本，登录或注册
                    Text(if(isLoginMode) "登录" else "注册")
                }
            }

            Spacer(modifier = Modifier.height(8.dp))

            TextButton(
                onClick = { viewModel.toggleLoginMode() },
                enabled = !isLoading
            ) {
                Text(
                    if(isLoginMode) "没有账号？立即注册"
                    else "已有账号？去登录"
                )
            }
        }
    }
}

// 加上topBar，完整的登录认证页面
@Composable
fun LoginRegisterScreen(
    viewModel: AuthViewModel,   // 它的父组件SmartHealthApp传入，SmartHealthApp是由MainActivity传入
){
    Scaffold(
        topBar = { MedicalTopBar() }
    ) { innerPadding ->
        LoginRegisterContent(
            modifier = Modifier.padding(innerPadding),
            viewModel = viewModel
        )
    }
}