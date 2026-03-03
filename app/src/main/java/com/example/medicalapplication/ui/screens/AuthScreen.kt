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
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
// 认证页面组件
fun LoginRegisterScreen(modifier: Modifier = Modifier){
    // 定义状态
    // 必须要mutableStateOf，因为直接var isLogin=true只会被当普通变量，不被compose知晓，不会重组组件
    // 必须remember，因为compose组件重组组件时，代码从上往下执行，再执行一次var isLogin by mutableStateOf(true)相当于又把isLogin的值重置了，页面还是不会变化
    // by是语法糖，本质是get、set value
    var isLoginMode by remember { mutableStateOf(true) }    // true登录，false=注册，默认为登录
    var account by remember { mutableStateOf("") }      // “” 记录手机号输入框的初始输入数据
    var password by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }

    Column (
        modifier = Modifier.fillMaxSize().padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ){
        Text(
            text = if (isLoginMode) "欢迎登录" else "创建账号",
            style = MaterialTheme.typography.headlineMedium
        )
        Spacer(modifier = Modifier.height(24.dp))
        OutlinedTextField(      // 账号输入框
            value = account,
            //语法糖，该lambda表达式相当于{ (newAccount)->account=newAccount }
            onValueChange = {
                account = it },  // 当传入一个参数，且仅有赋值给一个变量的函数体逻辑时
            label = { Text("请输入手机号") },
            singleLine = true,
            modifier = Modifier.wrapContentWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))    //高8dp的空行
        OutlinedTextField(      // 密码输入框
            value = password,
            onValueChange = { password=it },
            label = {Text("密码")},
            singleLine = true,  // 单行输入行，不会自动换行
            visualTransformation = PasswordVisualTransformation(),
            modifier = Modifier.wrapContentWidth()
        )
        if (!isLoginMode){      // 如果不是登录就增加确认密码这一输入框
            Spacer(modifier = Modifier.height(8.dp))
            OutlinedTextField(
                value = confirmPassword,
                onValueChange = { confirmPassword = it },
                label = { Text("确认密码")},
                singleLine = true,
                visualTransformation = PasswordVisualTransformation(),
                modifier = Modifier.wrapContentWidth()
            )
        }
        Spacer(modifier = Modifier.height(8.dp))
        Button(     //主按钮
            onClick = {
                if (isLoginMode){   //暂时设置的点击事件，后续这里需要写网络请求、缓存登录状态和token等（连接ViewModel）
                    println("登录手机号=$account, 密码=$password")
                }else{
                    println("注册手机号=$account")
                }
                      },
            modifier = Modifier.fillMaxWidth(0.8f)
        ) {
            Text(if(isLoginMode) "登录" else "注册")
        }
        Spacer(modifier = Modifier.height(8.dp))
        TextButton(     // 切换模式的按钮（文本按钮）
            onClick = {
                isLoginMode = !isLoginMode
                account = ""
                password = ""
                confirmPassword = ""
            }
        ) {
            Text(
                if(isLoginMode) "没有账号？立即注册"
                else "已有账号？去登录"
            )
        }
    }
}